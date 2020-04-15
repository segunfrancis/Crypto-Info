package com.project.segunfrancis.crypto_info.dataSource.remote

import com.project.segunfrancis.crypto_info.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
interface RemoteSource {
    fun getRemoteData(): Call<ResponseData>
}