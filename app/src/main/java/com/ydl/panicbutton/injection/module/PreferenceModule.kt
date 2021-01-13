package com.osac.cs.injection.module

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ydl.panicbutton.repository.preference.PreferenceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {
    @Provides
    @Singleton
    fun giveSharedPreferences(preference: SharedPreferences, gson: Gson): PreferenceRepository {
        return PreferenceRepository(
            preference = preference,
            gson = gson
        )
    }
}