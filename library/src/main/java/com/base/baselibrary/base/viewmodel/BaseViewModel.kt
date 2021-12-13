package com.base.baselibrary.core.viewmodel

import androidx.lifecycle.ViewModel
import com.base.baselibrary.callback.livedata.EventLiveData

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */
open class BaseViewModel : ViewModel() {

    val loadingChange: UILoadChange by lazy { UILoadChange() }

    inner class UILoadChange {
        //显示加载
        val showLoading by lazy {
            EventLiveData<String>()
        }

        //隐藏加载
        val hideLoading by lazy {
            EventLiveData<Boolean>()
        }
    }
}