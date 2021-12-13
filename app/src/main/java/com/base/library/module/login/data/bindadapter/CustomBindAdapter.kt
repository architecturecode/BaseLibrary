package com.base.library.module.login.data.bindadapter

import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.databinding.BindingAdapter

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　:
 */
object CustomBindAdapter {

    @BindingAdapter(value =["checkChange"])
    @JvmStatic
    fun checkChange(checkBox: CheckBox,listener:CompoundButton.OnCheckedChangeListener){
        checkBox.setOnCheckedChangeListener(listener)
    }
}