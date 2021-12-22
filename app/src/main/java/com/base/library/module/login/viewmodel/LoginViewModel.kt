package com.base.library.module.login.viewmodel

import com.base.baselibrary.callback.livedata.BooleanLiveData
import com.base.baselibrary.callback.livedata.StringLiveData
import com.base.baselibrary.base.viewmodel.BaseViewModel

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　: state viewModel  主要记录状态类的数据
 */
class LoginViewModel : BaseViewModel() {

    var username = StringLiveData()

    var password = StringLiveData()

    var isAutoLogin = BooleanLiveData()
//    var isAutoLogin = BooleanObservableField()
//
//    var autoLoginState  =  object :ObservableBoolean(isAutoLogin){
//        override fun get(): Boolean {
//            return isAutoLogin.get()
//        }
//    }


}