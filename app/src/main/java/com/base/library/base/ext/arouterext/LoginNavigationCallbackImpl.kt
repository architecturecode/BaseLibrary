package com.base.library.base.ext.arouterext

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

import android.os.Bundle

import com.base.baselibrary.utils.log.LogUtils
import com.base.baselibrary.utils.log.Logger
import com.base.library.base.constants.RouterPath


/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/20
 * 描述　:
 */
class LoginNavigationCallbackImpl :NavigationCallback {
    override fun onFound(postcard: Postcard?) {
    }

    override fun onLost(postcard: Postcard?) {
    }

    override fun onArrival(postcard: Postcard?) {
    }

    override fun onInterrupt(postcard: Postcard?) {
        val targetPath = postcard!!.path
        Logger.v("wht",targetPath)
        val bundle = postcard!!.extras
        // 被登录拦截了下来了
        // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
//        ARouter.getInstance().build(RouterPath.ACT_LOGIN_URL)
//            .with(bundle)
//            .withString("targetPath", targetPath)
//            .greenChannel()
//            .navigation()
        ARouter.getInstance().build(RouterPath.ACT_LOGIN_URL)
            .with(bundle)
            .withString(RouterPath.Target_Path, targetPath)
                    .greenChannel()
            .navigation()
    }
}