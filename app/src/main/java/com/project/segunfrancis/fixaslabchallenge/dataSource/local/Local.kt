package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import com.project.segunfrancis.fixaslabchallenge.util.Resource

/**
 * Created by SegunFrancis
 */
class Local(private val dao: CryptoDao) : LocalSource {
    override fun getLocalData(): LiveData<Resource<List<BaseResponse>>?> {
        return dao.getCryptoList()
    }

    /**
     * Insert's response list into Room database
     * */
    override suspend fun setLocalData(responseList: Resource<List<BaseResponse>>?) {
        dao.insertCryptoList(responseList)
    }
}