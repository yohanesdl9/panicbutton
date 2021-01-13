package com.osac.cs.injection.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

import com.google.gson.Gson
import com.ydl.panicbutton.repository.preference.PreferenceRepository

import java.util.concurrent.TimeUnit

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class ContextModule(
    @get:Provides
    val giveContext: Context
) {
    @get:Provides
    val giveClient: OkHttpClient
    @get:Provides
    val giveGson: Gson
    @get:Provides
    val givePreference: SharedPreferences

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        this.giveClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()
        this.giveGson = Gson()
        this.givePreference = giveContext.getSharedPreferences(PreferenceRepository.PREFERENCE, MODE_PRIVATE)
    }
}
