package com.base.library.module.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.baselibrary.core.viewmodel.BaseViewModel
import com.base.baselibrary.ext.request
import com.base.baselibrary.states.ResultState
import com.base.library.app.network.apiService
import com.base.library.module.login.data.model.bean.LoginBean

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */
class RequestLoginViewModel : BaseViewModel() {

    var resultLogin = MutableLiveData<ResultState<LoginBean>>()

    fun loginReq(username: String, pwd: String, loginType: String) {
        request(
            { apiService.login(username, pwd, loginType) },
            resultLogin,
            true,
            "登录中..."
        )
    }
}