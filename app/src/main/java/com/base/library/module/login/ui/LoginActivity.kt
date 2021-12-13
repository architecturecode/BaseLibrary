package com.base.library.module.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.base.baselibrary.base.activity.BaseVmDbActivity
import com.base.baselibrary.ext.parseState
import com.base.library.SecondActivity
import com.base.library.R
import com.base.library.base.BaseActivity
import com.base.library.base.constants.RouterPath
import com.base.library.base.ext.hideSoftKeyboard
import com.base.library.base.ext.showMessage
import com.base.library.databinding.ActivityLoginBinding
import com.base.library.module.login.viewmodel.LoginViewModel
import com.base.library.module.login.viewmodel.RequestLoginViewModel

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　:
 */

@Route(path = RouterPath.ACT_LOGIN_URL)
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    @Autowired()
    @JvmField
    var targetPath:String?=null


    //添加activity或Fragment ktx依赖
    private val loginViewModel: RequestLoginViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_login

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

                    if (!targetPath.isNullOrEmpty()){
                       ARouter.getInstance().build(targetPath).navigation()
                    }

//                    Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show()
//                    finish()
                }, {
                    showMessage(it.errorMsg)
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

            targetPath?.let {
                ARouter.getInstance().build(targetPath).navigation()
                Log.i("eee", "账号不能为空")
            }
            Log.i("eee", "qqqq")



//            when {
//                viewModel.username.value?.isEmpty() == true -> {
//                    Log.i("eee", "账号不能为空")
//                }
//                viewModel.password.value?.isEmpty() == true -> {
//                    Log.i("eee", "密码不能为空")
//                }
//
//                else ->  loginViewModel.loginReq("17621321103","e10adc3949ba59abbe56e057f20f883e","1")
//
////                else -> loginViewModel.loginReq(
////                    viewModel.username.value.toString(),
//////                    encodeStringToMD5(viewModel.password.value.toString()),
////                    "25d55ad283aa400af464c76d713c07ad",
////                    "1"
////                )
//            }
        }

        fun goForgetPwd() {
            hideSoftKeyboard(this@LoginActivity)
            startActivity(Intent(this@LoginActivity, SecondActivity::class.java))
//            nav(btnLogin).navigateAction(R.id.btn_login)
        }

        var onCheckChangeLister =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAutoLogin.value = isChecked
                Log.i("wht", "check box state==${viewModel.isAutoLogin.value}")
            }
    }

}