package com.ydl.panicbutton.extension

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun glideCallback(onLoadFailed: (GlideException?, Any?, Target<Drawable>?, Boolean) -> Boolean = { _, _, _, _ -> false},
                  onResourceReady: (Drawable?, Any?, Target<Drawable>?, DataSource?, Boolean) -> Boolean =  { _, _, _, _, _ -> false},
                  onFinish: ()-> Unit = {}):
        RequestListener<Drawable> = object : RequestListener<Drawable>{
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        onFinish()
        return onLoadFailed(e, model, target, isFirstResource)
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        onFinish()
        return onResourceReady(resource, model, target, dataSource, isFirstResource)
    }
}