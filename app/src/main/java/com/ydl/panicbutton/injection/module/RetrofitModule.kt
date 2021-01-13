package com.osac.cs.injection.module

import com.ydl.panicbutton.BuildConfig
import com.google.gson.Gson
import com.ydl.panicbutton.repository.network.request.RemoteRepository

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun giveRetrofitBuilder(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    @Provides
    @Singleton
    fun giveRemoteRepository(retrofit: Retrofit): RemoteRepository {
        return retrofit.create(RemoteRepository::class.java)
    }
}
