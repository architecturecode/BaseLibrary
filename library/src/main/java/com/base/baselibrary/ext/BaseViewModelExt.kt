package com.base.baselibrary.ext

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.baselibrary.core.viewmodel.BaseViewModel
import com.base.baselibrary.network.dao.BaseResponse
import com.base.baselibrary.states.ResultState
import com.base.baselibrary.states.paresException
import com.base.baselibrary.states.paresResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */

fun <T> BaseViewModel.request(
    block:suspend()->BaseResponse<T>,
    resultState: MutableLiveData<ResultState<T>>,
    isShowLoading:Boolean=false,
    loadingMessage:String="请求网络中..."
):Job{
    return viewModelScope.launch {
        kotlin.runCatching {
            if (isShowLoading) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            resultState.paresResult(it)
        }.onFailure {
            Log.e("Request Failed",it.message!!)
            resultState.paresException(it)
        }
    }
}