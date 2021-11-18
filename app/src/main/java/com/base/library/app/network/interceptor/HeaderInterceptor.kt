package com.base.library.app.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　: 在Header中添加參數
 */
class HeaderInterceptor :Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain?.request()?.newBuilder()
        builder?.addHeader("token","dddddddddddddddddddddddd")
        return chain?.proceed(builder?.build())
    }
}