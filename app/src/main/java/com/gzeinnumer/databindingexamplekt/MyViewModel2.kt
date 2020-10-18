package com.gzeinnumer.databindingexamplekt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel2 : ViewModel() {
    var str = MutableLiveData<String>()
}