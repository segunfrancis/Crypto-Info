package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */
class Local(private val dao: CryptoDao) : LocalSource {
    override fun getLocalData(): LiveData<List<ApiResponse>> {
        return dao.getCryptoList()
    }

    /**
     * Insert's response list into Room database
     * */
    override suspend fun setLocalData(responseList: List<ApiResponse>) {
        dao.insertCryptoList(responseList)
    }
}