package com.gzeinnumer.databindingexamplekt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var str = MutableLiveData<String>()
}