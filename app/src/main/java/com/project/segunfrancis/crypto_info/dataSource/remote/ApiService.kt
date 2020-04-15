package com.project.segunfrancis.crypto_info.dataSource.remote

import com.project.segunfrancis.crypto_info.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by SegunFrancis
 */
interface ApiService {

    @Headers("X-CMC_PRO_API_KEY: abf48c40-111f-4705-875f-9948176fa3da")
    @GET("cryptocurrency/listings/latest")
    fun getCryptoCoins(): Call<ResponseData>
}