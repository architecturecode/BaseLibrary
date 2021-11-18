package com.base.baselibrary.net.dao

import java.io.Serializable

/**
 *  Created by wuhaitao on 2021/11/18
 */
open class BaseResponse<T> : Serializable {

    val error: String? = null

    val message: String? = null

    val data: T? = null


}