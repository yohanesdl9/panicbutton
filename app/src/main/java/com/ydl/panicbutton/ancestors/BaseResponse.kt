package com.ydl.panicbutton.ancestors

interface BaseResponse {
    fun status(): Boolean
    fun message(): String
}