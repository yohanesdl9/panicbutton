package com.ydl.panicbutton.repository.network.request

import com.ydl.panicbutton.repository.network.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RemoteRepository {
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): LoginResponse
}