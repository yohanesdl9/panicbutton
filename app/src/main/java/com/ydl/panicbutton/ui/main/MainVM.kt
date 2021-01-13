package com.ydl.panicbutton.ui.main

import com.ydl.panicbutton.MainApplication
import com.ydl.panicbutton.ancestors.BaseActivity
import com.ydl.panicbutton.ancestors.BaseViewModel

class MainVM(activity: BaseActivity): BaseViewModel(activity = activity) {

    init { (activity.application as MainApplication).consumer.inject(this) }
}