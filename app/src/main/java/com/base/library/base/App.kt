package com.base.library.base

import com.alibaba.android.arouter.launcher.ARouter
import com.base.baselibrary.base.BaseApp
import com.base.library.BuildConfig

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */
class App:BaseApp() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}