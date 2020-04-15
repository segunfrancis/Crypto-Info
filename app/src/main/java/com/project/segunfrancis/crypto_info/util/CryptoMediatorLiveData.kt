package com.project.segunfrancis.crypto_info.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Created by SegunFrancis
 */
class CryptoMediatorLiveData<String>(query: LiveData<String>) :
    MediatorLiveData<String?>() {
    init {
        addSource(query) {
            value = query.value
        }
    }
}