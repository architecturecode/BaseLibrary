package com.base.library.base.ext.arouterext

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.base.baselibrary.base.appContent
import com.base.baselibrary.utils.log.Logger
import com.base.library.base.constants.RouterFlag
import com.base.library.base.constants.RouterPath

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　: 拦截器
 */

@Interceptor(priority = 1)
class UserInterceptor : IInterceptor {
    override fun init(context: Context?) {
        Logger.d("UserInterceptor", "UserInterceptor拦截器---init")
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        val threadName: String = Thread.currentThread().name
        Logger.d("UserInterceptor", "UserInterceptor拦截器线程名称---$threadName")
        postcard?.timeout = 1
        val flag = postcard?.extra

        val targetPath = postcard!!.path
        Logger.d("UserInterceptor", "UserInterceptor拦截器目标地址---$targetPath")
        val bundle = postcard!!.extras

        if (flag?.and(RouterFlag.Flag_LOGIN)  != 0) { //需要拦截
            //
//            val isLogin :Boolean =
            val isLogin: Boolean = false
            if (!isLogin) {
                showToast("需要登录")

                callback?.onInterrupt(null)
                // 被登录拦截了下来了
                // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
            } else {
                callback?.onContinue(postcard)
            }
        } else {
            callback?.onContinue(postcard)
        }
    }

    private fun showToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(appContent, message, Toast.LENGTH_SHORT).show()
        }

    }
}