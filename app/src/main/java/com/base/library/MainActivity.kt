package com.base.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.base.baselibrary.base.activity.BaseActivity
import com.base.baselibrary.core.viewmodel.BaseViewModel
import com.base.library.module.login.viewmodel.RequestLoginViewModel



class MainActivity: BaseActivity<BaseViewModel>(){

    private lateinit var btnLogin:Button

    //添加activity或Fragment ktx依赖
    val loginViewModel:  RequestLoginViewModel by viewModels()

    override fun getLayoutId() =R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
       addLoadingObserve(loginViewModel)
        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener({
            loginViewModel.loginReq("17621321103","e10adc3949ba59abbe56e057f20f883e","1")
        })
    }

    override fun showLoading(message: String) {
    }

    override fun hideLoading() {
    }

//    override fun createObserver() {
//    }

}