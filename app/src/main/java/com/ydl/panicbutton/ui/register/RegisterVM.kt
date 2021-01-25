package com.ydl.panicbutton.ui.register

import com.ydl.panicbutton.MainApplication
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel

class RegisterVM(activity: BaseActivity): BaseViewModel(activity = activity) {

    init { (activity.application as MainApplication).consumer.inject(this) }
}