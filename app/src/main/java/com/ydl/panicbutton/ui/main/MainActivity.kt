package com.ydl.panicbutton.ui.main

import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ydl.panicbutton.R
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.extension.instantiateViewModel
import com.ydl.panicbutton.ui.login.LoginActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val vm by lazy { instantiateViewModel<MainVM>() }

    private val handler = Handler()

    val runnable: Runnable = Runnable(){
        fun run(){
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                    // Got last known location. In some rare situations this can be null.
                }
            } catch (ex: SecurityException) {
                Log.e("ERROR", ex.message!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        handler.postDelayed(runnable, 300000)

        vm.userLogout.observe(this, Observer{
            vm.preferenceRepository.removeUser()
            startActivity<LoginActivity>()
            finishAffinity()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuPengaturan -> {

                true
            }
            R.id.menuLogout -> {
                vm.logout()
                true
            }
            else -> super.onOptionsItemSelected(item);
        }
    }
}