package com.base.baselibrary.ext

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.baselibrary.base.activity.BaseVmActivity
import com.base.baselibrary.core.viewmodel.BaseViewModel
import com.base.baselibrary.network.AppException
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


/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */

fun <T> BaseVmActivity<*>.parseState(
    resultState: ResultState<T>,
    onSuccess:(T)->Unit,
    onError:((AppException)->Unit)?=null,
    onLoading:(()->Unit)? =null
){
    when(resultState){

        is ResultState.Loading->{
            showLoading(resultState.loadingMessage)
            onLoading?.run { this }
        }

        is ResultState.Success->{
            hideLoading()
            onSuccess(resultState.data)
        }
        is ResultState.Error->{
            hideLoading()
            onError?.run { this(resultState.error) }
        }
    }

}




/**
 *
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