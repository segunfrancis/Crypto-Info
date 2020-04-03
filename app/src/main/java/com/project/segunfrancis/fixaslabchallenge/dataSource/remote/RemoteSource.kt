package com.project.segunfrancis.fixaslabchallenge.dataSource.remote

import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */
interface RemoteSource {
    fun getRemoteData(): List<ApiResponse>?
}