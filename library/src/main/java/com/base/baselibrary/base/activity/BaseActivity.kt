package com.base.baselibrary.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.base.baselibrary.core.viewmodel.BaseViewModel
import com.base.baselibrary.ext.getVmClazz
import com.base.baselibrary.network.manager.NetState
import com.base.baselibrary.network.manager.NetworkStateManager

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　: ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: VM

    abstract fun getLayoutId(): Int

    abstract fun initViews(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "正在请求网络...")

    abstract fun hideLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?){
       viewModel =createViewModel()
        //
        registerUIChange()
        initViews(savedInstanceState)
        //
//        createObserver()
        //网络状态
        NetworkStateManager.instance.networkStateCallBack.observeInActivity(this) {
            onNetworkStateChanged(it)
        }
    }

    /**
     * 将非该Activity绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel){
        viewModels.forEach {viewModel ->
            //显示弹窗
            viewModel.loadingChange.showLoading.observeInActivity(this, Observer {
                showLoading(it)
            })
            //关闭弹窗
            viewModel.loadingChange.hideLoading.observeInActivity(this, Observer {
                hideLoading()
            })
        }
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建LiveData数据观察者
     */
//    abstract fun createObserver()

    /**
     * 注册UI事件
     */
    private fun registerUIChange() {
        //显示弹窗
        viewModel.loadingChange.showLoading.observeInActivity(this, this::showLoading)
        //隐藏弹窗
        viewModel.loadingChange.hideLoading.observeInActivity(
            this
        ) { hideLoading() }
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }



}