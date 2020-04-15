package com.project.segunfrancis.crypto_info.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.crypto_info.model.BaseResponse

/**
 * Created by SegunFrancis
 */
interface LocalSource {
    /**
     * Insert's response list into Room database
     * */
    suspend fun setLocalData(responseList: List<BaseResponse>?)

    /**
     * Searches through the list of coins and returns coins that matches query [String]
     * @param query is the search string
     *
     * There is no need to get the full list of coins because when the @param query is empty,
     * it will display all the entire list of coins
     */
    fun getSearchResults(query: String?): LiveData<List<BaseResponse>>
}