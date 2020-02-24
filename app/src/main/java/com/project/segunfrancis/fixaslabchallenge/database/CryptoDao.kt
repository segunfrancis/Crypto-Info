package com.project.segunfrancis.fixaslabchallenge.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto_table")
    fun getCryptoList(): LiveData<List<ApiResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoList(responseList: List<ApiResponse?>)
}