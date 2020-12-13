package com.platzi.conf.network

import com.platzi.conf.model.Conference
import java.lang.Exception

interface Callback<T> {
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)
}