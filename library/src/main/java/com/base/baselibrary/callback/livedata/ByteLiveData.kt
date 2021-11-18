package com.base.baselibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:自定义的Boolean类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class ByteLiveData : MutableLiveData<Byte>() {
    override fun getValue(): Byte? {
        return super.getValue() ?: 0
    }
}