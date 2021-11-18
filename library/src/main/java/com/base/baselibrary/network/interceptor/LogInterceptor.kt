package com.base.baselibrary.network.interceptor

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import okio.EOFException
import okio.GzipSource
import okio.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 *  Created by wuhaitao on 2021/11/18
 *
 *  日志拦截器
 */
class LogInterceptor(val logEnable: Boolean) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!logEnable) return chain.proceed(chain.request())
        //
        val request = chain.request()
        val method = request.method
        val url = request.url
        Log.i("HttpLogging", "【Http】 Request start")
        Log.i("HttpLogging", "【Http】 $method $url")

        val connection = chain.connection()
        val protocol = connection?.protocol()
        protocol?.let { Log.i("HttpLogging", "【Http】 protocol:$it") }

        val requestHeader = request.headers
        requestHeader.forEach {
            Log.i("HttpLogging", "【Http】[header]${it.first}:${it.second}")
        }

        val reqBody = request.body
        reqBody?.contentType()?.let {
            Log.i("HttpLogging", "【Http】contentType${it}")
        }
        reqBody?.contentLength()?.let {
            Log.i("HttpLogging", "【Http】contentLength${it}")
        }
        Log.i("HttpLogging", "【Http】Request $method end")

        if ("POST"==method){
            Log.i("HttpLogging", "【Http】[body content]${bodyToString(request)}")
        }


        //
        Log.i("HttpLogging", "【Http】Response $method start")
        val startNs = System.nanoTime()
        //
        var response: Response

        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            Log.i("HttpLogging", "【Http】Failed e:${e.message}")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        Log.i("HttpLogging", "【Http】code:${response.code}($tookMs ms)")

        //headers
        val responseHeaders = response.headers
        responseHeaders.forEach {
            Log.i("HttpLogging", "【Http】[header]${it.first}:${it.second}")
        }

        //
        var responseBody = response.body
        response.message.let {
            if (!it.isNullOrEmpty()) {
                Log.i("HttpLogging", "【Http】[message]${it}")
            }
        }

        val contentLength = responseBody?.contentLength()
        val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"

        if (a(responseHeaders)) {
            Log.i("HttpLogging", "【Http】Response $method end ($bodySize)")
        } else {
            val source = responseBody?.source()
            source?.request(9223372036854775807L)
            var buffer = source?.buffer
            var gzippedLength = 0L
            if ("gzip".equals(responseHeaders["Content-Encoding"], true)) {
                gzippedLength = buffer?.size!!
                var gzippedResponseBody: GzipSource? = null
                try {
                    gzippedResponseBody = GzipSource(buffer.clone())
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                } finally {
                    gzippedResponseBody?.close()
                }
            }
            var mediaType = responseBody?.contentType()
            var charset = mediaType?.charset(Charset.forName("UTF-8"))

//            if (buffer != null && a(buffer)) {
            if (buffer != null ) {
                Log.i("HttpLogging", "【Http】Response $method end")
                return response
            }
            if (contentLength != 0L) {
                charset?.let {
                    Log.i("HttpLogging", "【Http】msg :${buffer?.clone()?.readString(it)}")
                }
            }
            Log.i(
                "HttpLogging", "【Http】Response $method end (${buffer?.size}-byte"
                        + if (gzippedLength != 0L) ", $gzippedLength--gzipped-byte)" else ")"
            )
        }
        return response
    }

    private fun a(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"]
        return contentEncoding != null && !contentEncoding.equals(
            "identity",
            true
        ) && !contentEncoding.equals("gzip", true)
    }

    private fun a(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteContent = if (buffer.size < 64L) buffer.size else 64L
            Log.i(
                "HttpLogging", "【Http】Response byteContent-byte"+byteContent)

            var i = 0
            while (i < 16 && prefix.exhausted()) {
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
                i++
            }
            true
        } catch (var6: EOFException) {
            false
        }
    }

    private fun bodyToString(request: Request): String {
        return try {
            val copyRequest= request?.newBuilder()?.build()
            val buffer:Buffer = Buffer()
            copyRequest.body?.writeTo(buffer)
            buffer.readUtf8()
        }catch (e:IOException){
            "error"
        }
    }
}