package com.ydl.panicbutton.extension

import androidx.lifecycle.ViewModelProvider
import com.mcnmr.utilities.extension.isDenied
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel
import com.ydl.panicbutton.factory.ViewModelFactory

inline fun <reified T: BaseViewModel> BaseActivity.instantiateViewModel(): T =
    ViewModelProvider(this, ViewModelFactory(this)).get(T::class.java)

fun Array<out String>.isPermissionGranted(grantResults: IntArray): Boolean{
    this.forEachIndexed { index, _ ->
        if(grantResults[index].isDenied()){
            return false
        }
    }
    return true
}