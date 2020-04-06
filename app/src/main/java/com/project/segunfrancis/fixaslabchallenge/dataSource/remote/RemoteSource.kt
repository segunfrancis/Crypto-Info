package com.project.segunfrancis.fixaslabchallenge.dataSource.remote

import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
interface RemoteSource {
    fun getRemoteData(): Call<ResponseData>
}