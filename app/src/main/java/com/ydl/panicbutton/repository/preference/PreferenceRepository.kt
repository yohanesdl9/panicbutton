package com.ydl.panicbutton.repository.preference

import android.content.SharedPreferences
import com.google.gson.Gson

class PreferenceRepository(private val preference: SharedPreferences, private val gson: Gson)  {
    companion object {
        const val PREFERENCE = "PREFERENCE"
        const val USER = "USER"
    }
}