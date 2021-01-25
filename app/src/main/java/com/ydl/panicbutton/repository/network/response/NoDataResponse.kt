package com.ydl.panicbutton.repository.network.response

import com.google.gson.annotations.SerializedName
import com.ydl.panicbutton.ancestors.BaseResponse

data class NoDataResponse(@SerializedName("status")
                          val status: Boolean,
                          @SerializedName("message")
                          val message: String): BaseResponse {
    override fun status(): Boolean = status
    override fun message(): String = message
}