package com.project.segunfrancis.crypto_info.useCases

import androidx.lifecycle.LiveData
import com.project.segunfrancis.crypto_info.dataSource.DataRepository
import com.project.segunfrancis.crypto_info.model.BaseResponse

/**
 * Created by SegunFrancis
 */
class GetLocalData(private val dataRepo: DataRepository) {

    fun getSearchResults(query: String?): LiveData<List<BaseResponse>> {
        return dataRepo.getSearchResults(query)
    }
}