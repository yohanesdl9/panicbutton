package com.ydl.panicbutton.extension

import androidx.lifecycle.LiveData

class NonNullLiveData<T>(value: T): LiveData<T>(value) {

    override fun getValue(): T { return super.getValue()!! }

    public override fun postValue(value: T) { super.postValue(value) }

    public override fun setValue(value: T) { super.setValue(value) }

}