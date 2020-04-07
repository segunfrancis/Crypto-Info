package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse

/**
 * Created by SegunFrancis
 */
class Local(private val dao: CryptoDao) : LocalSource {
    override fun getLocalData(): LiveData<List<BaseResponse>> {
        return dao.getCryptoList()
    }

    /**
     * Insert's response list into Room database
     * */
    override suspend fun setLocalData(responseList: List<BaseResponse>?) {
        dao.insertCryptoList(responseList)
    }
}