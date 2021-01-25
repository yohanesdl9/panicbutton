package com.ydl.panicbutton.repository.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ydl.panicbutton.repository.network.response.LoginResponse

class PreferenceRepository(private val preference: SharedPreferences, private val gson: Gson)  {
    companion object {
        const val PREFERENCE = "PREFERENCE"
        const val USER = "USER"
    }

    fun saveUser(user: LoginResponse.LoginData){
        preference.edit().putString(USER, gson.toJson(user)).apply()
    }

    fun getUser(): LoginResponse.LoginData?{
        return if(preference.contains(USER)){
            gson.fromJson(preference.getString(USER, ""), LoginResponse.LoginData::class.java)
        }else{
            null
        }
    }

    fun removeUser(){
        preference.edit().remove(USER).apply()
    }
}