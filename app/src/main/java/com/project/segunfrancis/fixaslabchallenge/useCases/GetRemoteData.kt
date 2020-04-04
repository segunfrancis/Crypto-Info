package com.project.segunfrancis.fixaslabchallenge.useCases

import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository

/**
 * Created by SegunFrancis
 */
class GetRemoteData(private val dataRepo: DataRepository) {
    fun getDataFromRemote() {
        dataRepo.getRemoteData()
    }
}