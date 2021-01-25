package com.ydl.panicbutton.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mcnmr.utilities.extension.isNotEmptyOrNotNull
import com.mcnmr.utilities.internal_plugin.combineLiveData
import com.mcnmr.utilities.wrapper.SingleEventWrapper
import com.ydl.panicbutton.MainApplication
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel
import com.ydl.panicbutton.repository.network.request.RemoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterVM(activity: BaseActivity): BaseViewModel(activity = activity) {

    companion object{
        const val REGISTER_REQUEST = "REGISTER_REQUEST"
    }

    @Inject
    lateinit var remoteRepository: RemoteRepository

    val namaData = MutableLiveData<String>()
    val teleponData = MutableLiveData<String>()
    val emailData = MutableLiveData<String>()
    val passwordData = MutableLiveData<String>()
    val retypePasswordData = MutableLiveData<String>()
    val successRegister = SingleEventWrapper<Void>()

    val validation = combineLiveData(namaData, teleponData, emailData, passwordData, retypePasswordData) {
        a, b, c, d, e -> a.isNotEmptyOrNotNull() && b.isNotEmptyOrNotNull() && c.isNotEmptyOrNotNull() && (d.isNotEmptyOrNotNull() && e.isNotEmptyOrNotNull()) && (d.equals(e))
    }

    init { (activity.application as MainApplication).consumer.inject(this) }

    fun register() {
        viewModelScope.launch {
            activity.shouldShowLoading(REGISTER_REQUEST)
            val result = safeApiCall(REGISTER_REQUEST) {
                remoteRepository.register(
                    nama = namaData.value!!,
                    telepon = teleponData.value!!,
                    email = emailData.value!!,
                    password = passwordData.value!!
                )
            }
            activity.shouldHideLoading(REGISTER_REQUEST)
            result.consume(
                onUnknownError = activity::onUnknownError,
                onTimeoutError = activity::onTimeoutError,
                onNetworkError = activity::onNetworkError,
                onHttpError = activity::onHttpError,
                onErrorResponse = activity::onErrorResponse,
                onSuccess = {
                    successRegister.trigger()
                }
            )
        }
    }
}