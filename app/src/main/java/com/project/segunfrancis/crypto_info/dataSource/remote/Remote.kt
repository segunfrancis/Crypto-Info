package com.project.segunfrancis.crypto_info.dataSource.remote

import com.project.segunfrancis.crypto_info.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
class Remote(private val service: ApiService) : RemoteSource {
    override fun getRemoteData(): Call<ResponseData> {
        return service.getCryptoCoins()
    }
}