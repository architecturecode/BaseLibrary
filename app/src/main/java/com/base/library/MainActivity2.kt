package com.base.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.base.baselibrary.base.activity.BaseVmDbActivity
import com.base.baselibrary.ext.commom.encodeStringToMD5
import com.base.baselibrary.ext.nav
import com.base.baselibrary.ext.parseState
import com.base.library.base.ext.hideSoftKeyboard
import com.base.library.base.ext.showMessage
import com.base.library.databinding.ActivityMain2Binding
import com.base.library.module.login.viewmodel.LoginViewModel
import com.base.library.module.login.viewmodel.RequestLoginViewModel


class MainActivity2 : BaseVmDbActivity<LoginViewModel, ActivityMain2Binding>() {


    //添加activity或Fragment ktx依赖
    private val loginViewModel: RequestLoginViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_main2

    override fun initViews(savedInstanceState: Bundle?) {
        addLoadingObserve(loginViewModel)
        mDataBinding.viewmodel = viewModel
        mDataBinding.click = ProxyClick()
    }

    override fun showLoading(message: String) {
    }

    override fun hideLoading() {
    }

    override fun createObserver() {
        loginViewModel.resultLogin.observe(this,
            Observer { resultState ->
                parseState(resultState, {
                    //关闭当前页面

                    showMessage("登录成功")

//                    Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show()
//                    finish()
                }, {
                    Log.e("wht", it.errorMsg)
                })
            })
    }

    inner class ProxyClick {

        fun clear() {
            viewModel.username.value = ""
            viewModel.password.value = ""
        }

        fun login() {
            when {
                viewModel.username.value?.isEmpty() == true -> {
                    Log.i("eee", "账号不能为空")
                }
                viewModel.password.value?.isEmpty() == true -> {
                    Log.i("eee", "密码不能为空")
                }

                else ->  loginViewModel.loginReq("17621321103","e10adc3949ba59abbe56e057f20f883e","1")

//                else -> loginViewModel.loginReq(
//                    viewModel.username.value.toString(),
////                    encodeStringToMD5(viewModel.password.value.toString()),
//                    "25d55ad283aa400af464c76d713c07ad",
//                    "1"
//                )
            }
        }

        fun goForgetPwd() {
            hideSoftKeyboard(this@MainActivity2)
            startActivity(Intent(this@MainActivity2, MainActivity2::class.java))
//            nav(btnLogin).navigateAction(R.id.btn_login)
        }

        var onCheckChangeLister =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAutoLogin.value = isChecked
                Log.i("wht", "check box state==${viewModel.isAutoLogin.value}")
            }
    }

}