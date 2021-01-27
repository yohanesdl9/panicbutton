package com.ydl.panicbutton.ui.main

import android.app.AlertDialog
import com.mcnmr.utilities.wrapper.SingleEventWrapper
import com.ydl.panicbutton.MainApplication
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel
import com.ydl.panicbutton.repository.network.request.RemoteRepository
import com.ydl.panicbutton.repository.preference.PreferenceRepository
import javax.inject.Inject

class MainVM(activity: BaseActivity): BaseViewModel(activity = activity) {

    @Inject
    lateinit var remoteRepository: RemoteRepository

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    val userLogout = SingleEventWrapper<Void>()

    init { (activity.application as MainApplication).consumer.inject(this) }

    fun logout(){
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Apakah Anda yakin ingin logout?")
        alertDialogBuilder.setPositiveButton("Ya") { _, _ ->
            userLogout.trigger()
        }
        alertDialogBuilder.setNegativeButton("Tidak") { _, _ ->

        }
        alertDialogBuilder.show()
    }
}