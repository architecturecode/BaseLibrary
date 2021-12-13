package com.base.baselibrary.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.base.baselibrary.core.viewmodel.BaseViewModel

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　: ViewModelActivity基类，把ViewModel注入进来了
 */
open  abstract class BaseVmDbActivity<VM : BaseViewModel,DB:ViewDataBinding> : BaseVmActivity<VM>() {

    lateinit var mDataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        userDataBinding(true)
        super.onCreate(savedInstanceState)
    }

    override fun initDataBind() {
        mDataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        mDataBinding.lifecycleOwner = this
    }


}