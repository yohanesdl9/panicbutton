package com.ydl.panicbutton.extension

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.isPermissionGranted(permissions: Array<String>): Boolean{
    var granted = true
    for(permission in permissions){
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            granted = false
            break
        }
    }
    return granted
}