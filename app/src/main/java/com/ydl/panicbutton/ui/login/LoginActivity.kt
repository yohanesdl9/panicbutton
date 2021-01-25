package com.ydl.panicbutton.ui.login

import android.os.Bundle
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding4.widget.textChanges
import com.ydl.panicbutton.R
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.extension.instantiateViewModel
import com.ydl.panicbutton.ui.main.MainActivity
import com.ydl.panicbutton.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.tilPassword
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity() {

    private val vm by lazy { instantiateViewModel<LoginVM>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        vm.validation.observe(this, Observer { btnLogin.isEnabled = it })

        tilEmailTelepon.editText!!.textChanges().subscribe { vm.usernameData.value = it.toString() }
        tilPassword.editText!!.textChanges().subscribe { vm.passwordData.value = it.toString() }

        btnLogin.setOnClickListener { vm.login() }

        tvRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        vm.successLogin.observe(this, Observer {
            startActivity<MainActivity>()
            finishAffinity()
        })
    }
}