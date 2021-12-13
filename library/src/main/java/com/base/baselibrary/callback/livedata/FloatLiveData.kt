package com.base.baselibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */
class FloatLiveData(value:Float =0f) :MutableLiveData<Float>(value) {
    override fun getValue(): Float? {
        return super.getValue()!!
    }
}