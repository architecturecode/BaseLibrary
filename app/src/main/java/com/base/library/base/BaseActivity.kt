package com.base.library.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.base.baselibrary.base.activity.BaseVmDbActivity
import com.base.baselibrary.base.viewmodel.BaseViewModel

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */
abstract class BaseActivity<VM: BaseViewModel,DB: ViewDataBinding>: BaseVmDbActivity<VM,DB>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }
}