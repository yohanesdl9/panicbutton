package com.ydl.panicbutton.wrapper

import com.ydl.panicbutton.ancestors.BaseResponse
import com.ydl.panicbutton.repository.network.response.ErrorResponseException
import java.io.IOException

sealed class ResultWrapper<out T: BaseResponse> {
    data class Success<out T: BaseResponse>(val value: T): ResultWrapper<T>()
    data class HttpError(val tag: Any, val code: Int, val error: String): ResultWrapper<Nothing>()
    data class UnknownError(val tag: Any, val message: String): ResultWrapper<Nothing>()
    data class ResponseError(val tag: Any, val error: ErrorResponseException): ResultWrapper<Nothing>()

    data class NetworkError(val tag: Any, val error: IOException): ResultWrapper<Nothing>()
    data class TimeoutError(val tag: Any): ResultWrapper<Nothing>()

    fun consume(onErrorResponse: (Any, ErrorResponseException) -> Unit = { _, _ -> },
                onNetworkError: (Any, IOException) -> Unit = { _, _ -> },
                onTimeoutError: (Any) -> Unit = { _ ->},
                onHttpError: (Any, Int, String) -> Unit = { _, _, _ -> },
                onUnknownError: (Any, String) -> Unit = { _, _ -> },
                onSuccess: (T) -> Unit){
        when(this){
            is Success -> onSuccess.invoke(value)
            is HttpError -> onHttpError.invoke(tag, code, error)
            is ResponseError -> onErrorResponse.invoke(tag, error)
            is NetworkError -> onNetworkError.invoke(tag, error)
            is TimeoutError -> onTimeoutError.invoke(tag)
            is UnknownError -> onUnknownError.invoke(tag, message)
        }
    }
}