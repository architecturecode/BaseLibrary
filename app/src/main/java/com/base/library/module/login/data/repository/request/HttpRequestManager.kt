package com.base.library.module.login.data.repository.request

import com.base.baselibrary.network.AppException
import com.base.library.app.network.apiService
import com.base.library.base.ApiResponse
import com.base.library.module.login.data.model.bean.LoginBean

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:处理协程的请求类
 */


val LoginRequestCoroutine: LoginHttpRequestManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    LoginHttpRequestManager()
}

class LoginHttpRequestManager {

    //
    suspend fun login(
        customerID: String,
        md5Pwd: String,
        loginType: String
    ): ApiResponse<LoginBean> {
        val loginData = apiService.login(customerID, md5Pwd, loginType)
        if (loginData.isSuccess()) {
            return apiService.getPersonalInfo()
        } else {
            val code = loginData.error.toIntOrNull()
            throw AppException(code, loginData.message)
        }
    }


}