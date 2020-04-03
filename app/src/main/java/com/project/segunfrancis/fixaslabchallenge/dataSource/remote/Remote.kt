package com.project.segunfrancis.fixaslabchallenge.dataSource.remote

import android.util.Log
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SegunFrancis
 */
class Remote(private val service: ApiService) : RemoteSource {
    private var responseList: List<ApiResponse>? = ArrayList()
    override fun getRemoteData(): List<ApiResponse>? {
        service.getCryptoCoins().enqueue(object : Callback<List<ApiResponse>?> {
            override fun onResponse(
                call: Call<List<ApiResponse>?>,
                response: Response<List<ApiResponse>?>
            ) {
                responseList = response.body()
            }

            override fun onFailure(call: Call<List<ApiResponse>?>, t: Throwable) {
                Log.e("Remote", t.localizedMessage!!)
            }
        })
        return responseList
    }
}