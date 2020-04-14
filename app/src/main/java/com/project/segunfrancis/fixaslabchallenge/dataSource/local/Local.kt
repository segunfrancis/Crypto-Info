package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse

/**
 * Created by SegunFrancis
 */
class Local(private val dao: CryptoDao) : LocalSource {

    /**
     * Insert's response list into Room database
     * */
    override suspend fun setLocalData(responseList: List<BaseResponse>?) {
        dao.insertCryptoList(responseList)
    }

    /**
     * Searches through the list of coins and returns coins that matches query [String]
     * @param query is the search string
     *
     * There is no need to get the full list of coins because when the @param query is empty,
     * it will display all the entire list of coins
     */
    override fun getSearchResults(query: String?): LiveData<List<BaseResponse>> {
        return dao.search(query)
    }
}