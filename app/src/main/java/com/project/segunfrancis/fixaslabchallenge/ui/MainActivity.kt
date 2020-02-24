package com.project.segunfrancis.fixaslabchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var repository: CryptoRepository
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.isRefreshing = true

        apiService = ApiBuilder.retrofit.create(ApiService::class.java)
        repository = CryptoRepository(apiService)

        getCryptoCoins()
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        getCryptoCoins()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            swipeRefresh.isRefreshing = true
            getCryptoCoins()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCryptoCoins() {
        val adapter = CryptoAdapter()
        recyclerView.adapter = adapter
        repository.getCryptoCoins().enqueue(object : Callback<List<ApiResponse>?> {
            override fun onResponse(
                call: Call<List<ApiResponse>?>,
                response: Response<List<ApiResponse>?>
            ) {
                adapter.setData(response.body())
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<ApiResponse>?>, t: Throwable) {
                // Display cached data
                swipeRefresh.isRefreshing = false
            }
        })
    }
}
