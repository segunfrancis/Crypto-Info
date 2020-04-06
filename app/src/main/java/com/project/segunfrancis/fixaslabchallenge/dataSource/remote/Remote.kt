package com.project.segunfrancis.fixaslabchallenge.dataSource.remote

import android.util.Log
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SegunFrancis
 */
class Remote(private val service: ApiService) : RemoteSource {
    private var responseList: List<BaseResponse> = ArrayList()
    override fun getRemoteData(): Call<ResponseData> {
        return service.getCryptoCoins()
        /*service.getCryptoCoins().enqueue(object : Callback<ResponseData?> {
            override fun onResponse(
                call: Call<ResponseData?>,
                response: Response<ResponseData?>
            ) {
                responseList = response.body()!!.data
                Log.d("RemoteCode", response.code().toString())
                GlobalScope.launch { dao.insertCryptoList(responseList) }
            }

            override fun onFailure(call: Call<ResponseData?>, t: Throwable) {
                Log.e("Remote", t.localizedMessage!!)
            }
        })*/
    }
}