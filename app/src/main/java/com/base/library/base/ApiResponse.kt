package com.base.library.base

import com.base.baselibrary.network.dao.BaseResponse
import java.lang.NumberFormatException

/**
 *  Created by wuhaitao on 2021/11/18
 */
data class ApiResponse<T>(val error: String, val message: String, val data: T) :
    BaseResponse<T>() {

    override fun isSuccess() = error.equals("0")

    override fun getResponseData() = data

    override fun getResponseCode(): Int? {
        try {
            return "error".toIntOrNull()
        } catch (e: NumberFormatException) {
            return -99999
        }
    }

    override fun getResponseMsg() = message

}