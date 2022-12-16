package com.imaginato.homeworkmvvm.data.remote.login

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface Demo2Api {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Header("IMSI") imsi: Long,
        @Header("IMEI") imei: Long,
        @Field("") userName: String,
        @Field("") password: String
    ): Response<LoginResponse>
}