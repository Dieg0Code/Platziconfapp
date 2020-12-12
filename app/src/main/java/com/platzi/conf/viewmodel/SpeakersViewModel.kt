package com.platzi.conf.viewmodel

import android.telecom.Conference
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Speaker
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase() {
         firestoreService.getSpeakers(object : Callback<List<Speaker>> {
             override fun onSuccess(result: List<Speaker>?) {
                 listSpeaker.postValue(result)
                 processFinished()
             }

             override fun onFailed(exception: Exception) {
                 processFinished()
             }
         })
    }

    fun processFinished() {
        isLoading.value = true
    }
}