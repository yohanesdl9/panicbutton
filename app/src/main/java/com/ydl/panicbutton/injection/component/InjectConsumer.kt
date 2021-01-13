package com.ydl.panicbutton.injection.component

import com.osac.cs.injection.module.*
import com.ydl.panicbutton.ui.main.MainVM
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
}