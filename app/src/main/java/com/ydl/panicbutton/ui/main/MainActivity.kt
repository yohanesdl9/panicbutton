package com.ydl.panicbutton.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ydl.panicbutton.R
import com.ydl.panicbutton.ancestors.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}