package com.ydl.panicbutton.ancestors

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.callbacks.onShow
import com.mcnmr.utilities.extension.dismissIfShowing
import com.mcnmr.utilities.extension.showIfNotShowing
import com.mcnmr.utilities.internal_plugin.obtainIntentData
import com.ydl.panicbutton.R
import com.ydl.panicbutton.custom_dialog.LoadingDialog
import com.ydl.panicbutton.repository.network.response.ErrorResponseException
import java.io.IOException

open class BaseActivity: AppCompatActivity() {
    companion object{
        const val FROM = "FROM"
    }

    private val loadingDialog by lazy { LoadingDialog(this).apply {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    } }

    open fun shouldShowLoading(tag: Any) = loadingDialog.showIfNotShowing()
    open fun shouldHideLoading(tag: Any) = loadingDialog.dismissIfShowing()
    open fun onErrorResponse(tag: Any, exception: ErrorResponseException){
        alertDialog(title = getString(R.string.alert_dialog_message_title),
            message = exception.message,
            positiveTitle = getString(R.string.alert_dialog_close))
    }
    open fun onNetworkError(tag: Any, exception: IOException){
        alertDialog(title = getString(R.string.alert_dialog_error_title),
            message = getString(R.string.alert_dialog_no_internet_message).format(exception.message),
            positiveTitle = getString(R.string.alert_dialog_close))
    }
    open fun onTimeoutError(tag: Any){
        alertDialog(title = getString(R.string.alert_dialog_error_title),
            message = getString(R.string.alert_dialog_rto_message),
            positiveTitle = getString(R.string.alert_dialog_close))
    }
    open fun onHttpError(tag: Any, httpCode: Int, message: String){
        alertDialog(title = getString(R.string.alert_dialog_http_error_title),
            message = getString(R.string.alert_dialog_http_error_message).format(httpCode, message),
            positiveTitle = getString(R.string.alert_dialog_close))
    }
    open fun onUnknownError(tag: Any, message: String){
        alertDialog(title = getString(R.string.alert_dialog_error_title),
            message = getString(R.string.alert_dialog_unknown_error).format(message),
            positiveTitle = getString(R.string.alert_dialog_close))
    }
    open fun onOptionsSelected(item: MenuItem){}

    protected open fun shouldCatchForceClose(): Boolean = true

    protected fun previousActivity(): String = intent.getStringExtra(FROM) ?: "null"

    fun alertDialog(title: String? = null,
                    message: String? = null,
                    negativeTitle: String? = null,
                    negativeAction: (() -> Unit)? = null,
                    positiveTitle: String? = null,
                    positiveAction: (() -> Unit)? = null,
                    onShow: (() -> Unit)? = null,
                    onDismiss: (() -> Unit)? = null,
                    cancelable: Boolean = true){
        MaterialDialog(this).show {
            title?.let{
                title(text = title)
            }
            message?.let{
                message(text = message)
            }
            negativeTitle?.let{
                negativeButton(text = negativeTitle) { dialog ->
                    dialog.dismiss()
                    negativeAction?.invoke()
                }
            }
            positiveTitle?.let {
                positiveButton(text = positiveTitle) { dialog ->
                    dialog.dismiss()
                    positiveAction?.invoke()
                }
            }
        }.onShow { onShow?.invoke() }.onDismiss { onDismiss?.invoke() }.apply {
            cancelable(cancelable)
            cancelOnTouchOutside(cancelable)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        obtainIntentData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
            else -> onOptionsSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}