package com.project.segunfrancis.crypto_info.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.segunfrancis.crypto_info.model.BaseResponse

/**
 * Created by SegunFrancis
 */

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoList(responseList: List<BaseResponse>?)

    @Query("SELECT * FROM crypto_table WHERE name LIKE :query")
    fun search(query: String?): LiveData<List<BaseResponse>>
}