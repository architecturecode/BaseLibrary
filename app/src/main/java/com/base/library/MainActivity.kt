package com.base.library

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.base.baselibrary.base.activity.BaseVmActivity
import com.base.baselibrary.base.appContent
import com.base.library.base.constants.RouterPath
import com.base.library.base.ext.arouterext.LoginNavigationCallbackImpl
import com.base.library.base.ext.hideSoftKeyboard
import com.base.library.module.login.viewmodel.LoginViewModel
import com.base.library.module.login.viewmodel.RequestLoginViewModel


@Route(path = RouterPath.ACT_MAIN_URL)
class MainActivity : BaseVmActivity<LoginViewModel>() {

    private lateinit var btnLogin: Button
    private lateinit var checkBox: CheckBox
    private lateinit var phone: EditText
    private lateinit var pwd: EditText

    private val pClick = ProxyClick()

    //添加activity或Fragment ktx依赖
    private val loginViewModel: RequestLoginViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {
        addLoadingObserve(loginViewModel)
        phone = findViewById(R.id.name)
        pwd = findViewById(R.id.pwd)
        btnLogin = findViewById(R.id.btn_login)
        checkBox = findViewById(R.id.checkBox)
        btnLogin.setOnClickListener {
            pClick.goForgetPwd()
        }

        checkBox.setOnCheckedChangeListener(pClick.onCheckChangeLister)

        checkBox.isChecked = true
        viewModel.isAutoLogin.value = true
    }

    override fun showLoading(message: String) {
    }

    override fun hideLoading() {
    }

    override fun createObserver() {

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
                else -> loginViewModel.loginReq(
                    viewModel.username.toString(), viewModel.password.toString(), "1"
                )
            }
        }

        fun goForgetPwd() {
            hideSoftKeyboard(this@MainActivity)

            ARouter.getInstance().build(RouterPath.ACT_SECOND_URL).navigation(appContent,LoginNavigationCallbackImpl())
//            ARouter.getInstance().build(RouterPath.ACT_SECOND_URL).navigation()
//            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
//            nav(btnLogin).navigateAction(R.id.btn_login)
        }

        var onCheckChangeLister =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAutoLogin.value = isChecked
                Log.i("wht", "check box state==${viewModel.isAutoLogin.value}")
            }
    }

}