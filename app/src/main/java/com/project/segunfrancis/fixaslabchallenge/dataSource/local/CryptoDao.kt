package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import com.project.segunfrancis.fixaslabchallenge.util.Resource

/**
 * Created by SegunFrancis
 */

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto_table")
    fun getCryptoList(): LiveData<Resource<List<BaseResponse>>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoList(responseList: Resource<List<BaseResponse>>?)
}