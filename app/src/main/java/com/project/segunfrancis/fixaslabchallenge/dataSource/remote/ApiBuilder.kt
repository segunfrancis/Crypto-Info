package com.project.segunfrancis.fixaslabchallenge.dataSource.remote

import com.project.segunfrancis.fixaslabchallenge.util.AppConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by SegunFrancis
 */
object ApiBuilder {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}