package com.ydl.panicbutton.injection.component

import com.osac.cs.injection.module.*
import com.ydl.panicbutton.ui.login.LoginVM
import com.ydl.panicbutton.ui.main.MainVM
import com.ydl.panicbutton.ui.register.RegisterVM
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    RetrofitModule::class,
    PreferenceModule::class
])
interface InjectConsumer {
    fun inject(vm: MainVM)
    fun inject(vm: LoginVM)
    fun inject(vm: RegisterVM)
}