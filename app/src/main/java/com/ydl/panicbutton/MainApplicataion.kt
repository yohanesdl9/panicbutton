package com.ydl.panicbutton

import android.app.Application
import com.osac.cs.injection.module.ContextModule
import com.ydl.panicbutton.injection.component.InjectConsumer
import com.ydl.panicbutton.injection.component.DaggerInjectConsumer

class MainApplication : Application(){
    lateinit var consumer: InjectConsumer

    override fun onCreate() {
        super.onCreate()
        consumer = DaggerInjectConsumer
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}