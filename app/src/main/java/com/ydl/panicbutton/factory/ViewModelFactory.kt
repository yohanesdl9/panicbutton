package com.ydl.panicbutton.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel
import com.ydl.panicbutton.ui.main.MainVM

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val activity: BaseActivity): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
        MainVM::class.java -> MainVM(activity = activity) as T
        else -> BaseViewModel(activity = activity) as T
    }
}