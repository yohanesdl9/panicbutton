package com.ydl.panicbutton.ancestors

import androidx.lifecycle.ViewModel
import com.ydl.panicbutton.MainApplication
import com.ydl.panicbutton.repository.network.response.ErrorResponseException
import com.ydl.panicbutton.wrapper.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

open class BaseViewModel(val activity: BaseActivity): ViewModel() {
    protected val app: MainApplication = activity.application as MainApplication

    protected suspend fun <T: BaseResponse> safeApiCall(tag: Any,
                                                        dispatcher: CoroutineDispatcher = Dispatchers.IO,
                                                        apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(dispatcher){
            try{
                val result = apiCall.invoke()
                if(!result.status()) {
                    throw ErrorResponseException(message = result.message())
                }else {
                    ResultWrapper.Success(value = result)
                }
            }catch (e: Exception){
                e.printStackTrace()
                when(e){
                    is IOException -> ResultWrapper.NetworkError(tag = tag, error = e)
                    is HttpException -> {
                        val code = e.code()
                        val errorResponse = e.message()
                        ResultWrapper.HttpError(tag, code = code, error = errorResponse)
                    }
                    is ErrorResponseException -> ResultWrapper.ResponseError(tag = tag, error = e)
                    else -> ResultWrapper.UnknownError(tag = tag, message = e.message ?: "No message")
                }
            }
        }
    }
}