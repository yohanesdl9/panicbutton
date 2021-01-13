package com.ydl.panicbutton.custom_dialog

import com.ydl.panicbutton.R
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.mcnmr.utilities.extension.getColor
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog (context: Context) : AlertDialog(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_loading, null))

        setCancelable(false)
        setCanceledOnTouchOutside(false)

        pbLoading.isIndeterminate = true
        pbLoading.indeterminateDrawable.colorFilter = PorterDuffColorFilter(getColor(R.color.black),
            PorterDuff.Mode.SRC_IN)

    }

    override fun show() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.show()
    }
}