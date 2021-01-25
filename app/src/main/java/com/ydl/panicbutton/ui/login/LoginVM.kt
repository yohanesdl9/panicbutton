package com.ydl.panicbutton.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mcnmr.utilities.extension.isNotEmptyOrNotNull
import com.mcnmr.utilities.internal_plugin.combineLiveData
import com.mcnmr.utilities.wrapper.SingleEventWrapper
import com.ydl.panicbutton.MainApplication
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel
import com.ydl.panicbutton.repository.network.request.RemoteRepository
import com.ydl.panicbutton.repository.preference.PreferenceRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginVM(activity: BaseActivity): BaseViewModel(activity = activity) {

    companion object{
        const val LOGIN_REQUEST = "LOGIN_REQUEST"
    }

    @Inject
    lateinit var remoteRepository: RemoteRepository

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    val usernameData = MutableLiveData<String>()
    val passwordData = MutableLiveData<String>()
    val successLogin = SingleEventWrapper<Void>()

    val validation = combineLiveData(usernameData, passwordData){
            a, b -> a.isNotEmptyOrNotNull() && b.isNotEmptyOrNotNull()
    }

    init { (activity.application as MainApplication).consumer.inject(this) }

    fun login() {
        viewModelScope.launch {
            activity.shouldShowLoading(tag = LOGIN_REQUEST)
            val result = safeApiCall(tag = LOGIN_REQUEST){
                remoteRepository.login(
                    username = usernameData.value!!,
                    password = passwordData.value!!
                )
            }
            activity.shouldHideLoading(tag = LOGIN_REQUEST)
            result.consume(
                onUnknownError = activity::onUnknownError,
                onTimeoutError = activity::onTimeoutError,
                onNetworkError = activity::onNetworkError,
                onHttpError = activity::onHttpError,
                onErrorResponse = activity::onErrorResponse,
                onSuccess = {
                    preferenceRepository.saveUser(it.data)
                    successLogin.trigger()
                }
            )
        }
    }
}