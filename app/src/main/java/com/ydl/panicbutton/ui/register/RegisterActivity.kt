package com.ydl.panicbutton.ui.register

import android.os.Bundle
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding4.widget.textChanges
import com.ydl.panicbutton.R
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.extension.instantiateViewModel
import com.ydl.panicbutton.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity

class RegisterActivity : BaseActivity() {

    private val vm by lazy { instantiateViewModel<RegisterVM>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        vm.validation.observe(this, Observer { btnRegister.isEnabled = it })

        tilNama.editText!!.textChanges().subscribe { vm.namaData.value = it.toString() }
        tilTelepon.editText!!.textChanges().subscribe { vm.teleponData.value = it.toString() }
        tilEmail.editText!!.textChanges().subscribe { vm.emailData.value = it.toString() }
        tilPassword.editText!!.textChanges().subscribe { vm.passwordData.value = it.toString() }
        tilRetypePassword.editText!!.textChanges().subscribe { vm.retypePasswordData.value = it.toString() }

        btnRegister.setOnClickListener { vm.register() }

        tvLogin.setOnClickListener {
            startActivity<LoginActivity>()
            finishAffinity()
        }
    }
}