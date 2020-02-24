package com.project.segunfrancis.fixaslabchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.project.segunfrancis.fixaslabchallenge.R
import com.project.segunfrancis.fixaslabchallenge.adapter.CryptoAdapter
import com.project.segunfrancis.fixaslabchallenge.api.ApiBuilder
import com.project.segunfrancis.fixaslabchallenge.api.ApiService
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import com.project.segunfrancis.fixaslabchallenge.repository.CryptoRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var repository: CryptoRepository
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CryptoAdapter()
        recyclerView.adapter = adapter

        apiService =  ApiBuilder.retrofit.create(ApiService::class.java)
        repository = CryptoRepository(apiService)
        repository.getCryptoCoins().enqueue(object : Callback<List<ApiResponse>?> {
            override fun onResponse(
                call: Call<List<ApiResponse>?>,
                response: Response<List<ApiResponse>?>
            ) {
                adapter.setData(response.body())
                hideProgressBar(progressBar)
            }

            override fun onFailure(call: Call<List<ApiResponse>?>, t: Throwable) {
                // Display cached data
                hideProgressBar(progressBar)
            }
        })
    }

    private fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    private fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }
}
