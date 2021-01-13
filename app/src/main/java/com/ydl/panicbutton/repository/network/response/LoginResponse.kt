package com.ydl.panicbutton.repository.network.response

import com.google.gson.annotations.SerializedName
import com.ydl.panicbutton.ancestors.BaseResponse
import java.io.Serializable

data class LoginResponse (
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val data: LoginData
): BaseResponse, Serializable {
    override fun status(): Boolean = status
    override fun message(): String = message

    data class LoginData(
        @SerializedName("id")
        val id: Int,
        @SerializedName("nama")
        val nama: String,
        @SerializedName("telepon")
        val telepon: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("username")
        val username: String,
    )
}