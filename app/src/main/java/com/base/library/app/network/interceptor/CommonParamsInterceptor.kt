package com.base.library.app.network.interceptor

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.StringBuilder

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　: 公共参数
 *        get 放在URL后拼接
 *        post 放在body里
 */
class CommonParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val commonParamsMap =
            mapOf(
                "version" to "1.0.0",
                "systemversion" to "8",
                "source" to "android",
//                "DeviceID" to "android",
                "snid" to "12345678"
            )

        var request = chain.request()
        val method = request?.method
        val oldUrl = request?.url.toString()
        if (method == "GET") {
            val stringBuilder = StringBuilder()
            stringBuilder.append(oldUrl)
            if (oldUrl.contains("?")) {//?在最后面....2类型
                if (oldUrl.indexOf("?") == oldUrl.length - 1) {
                } else { //3类型...拼接上&
                    stringBuilder.append("&")
                }
            } else {
                stringBuilder.append("?")
            }
            commonParamsMap.forEach { (key, value) ->
                stringBuilder.append(key).append("=").append(value).append("&")
            }
            if (stringBuilder.indexOf("&") != -1) {
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"))
            }
            val newUrl: String = stringBuilder.toString()
            request = request?.newBuilder()?.url(newUrl)?.build()
        } else if (method == ("POST")) {
            var requestBody = request?.body
            if (requestBody is FormBody) {
                val oldBody: FormBody = requestBody as FormBody //keywords...page
                val builder: FormBody.Builder = FormBody.Builder()
                //先添加之前的参数
                for (i in 0 until oldBody.size) {
                    //键值对的形式添加
                    builder.add(oldBody.name(i), oldBody.value(i))
                }
                //添加公共参数
                commonParamsMap.forEach { (key, value) ->
                    builder.add(key, value)
                }
                //构建一个新的请求
                request = request?.newBuilder()?.url(oldUrl)?.post(builder.build()).build()
            }
        }
        return chain?.proceed(request)
    }
}