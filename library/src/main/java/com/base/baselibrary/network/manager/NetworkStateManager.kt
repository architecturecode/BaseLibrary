package com.base.baselibrary.network.manager

import com.base.baselibrary.callback.livedata.EventLiveData

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:网络状态管理类,单例模式
 */
class NetworkStateManager private constructor() {

    val networkStateCallBack = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}