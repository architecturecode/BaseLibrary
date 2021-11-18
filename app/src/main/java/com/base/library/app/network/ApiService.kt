package com.base.library.app.network

import com.base.library.base.ApiResponse
import com.base.library.module.login.data.model.bean.LoginBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/18
 * 描述　:
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "http://192.168.63.118:9008/"
        const val SERVER_URL1 = "https://wanandroid.com/"
    }

    //登录接口
    @FormUrlEncoded
    @POST("IAccount/Login")
    suspend fun login(
        @Field("customerID") customerID: String,
        @Field("md5Pwd") md5Pwd: String, @Field("loginType") loginType: String
    ): ApiResponse<LoginBean>

 //登录接口
    @FormUrlEncoded
    @GET("IUserHome/index")
    suspend fun getPersonalInfo(): ApiResponse<LoginBean>


    //获取用户信息

}