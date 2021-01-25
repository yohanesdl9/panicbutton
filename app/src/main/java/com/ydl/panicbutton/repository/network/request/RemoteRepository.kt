package com.ydl.panicbutton.repository.network.request

import com.ydl.panicbutton.repository.network.response.LoginResponse
import com.ydl.panicbutton.repository.network.response.NoDataResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteRepository {
    @POST("login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): LoginResponse

    @POST("register")
    @FormUrlEncoded
    suspend fun register(@Field("nama") nama: String, @Field("telepon") telepon: String, @Field("email") email: String, @Field("password") password: String): NoDataResponse
}