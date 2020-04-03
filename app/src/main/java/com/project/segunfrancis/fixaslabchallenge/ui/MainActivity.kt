package com.project.segunfrancis.fixaslabchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.fixaslabchallenge.R
import com.project.segunfrancis.fixaslabchallenge.adapter.CryptoAdapter
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiService
import com.project.segunfrancis.fixaslabchallenge.repository.CryptoRepository
import com.project.segunfrancis.fixaslabchallenge.viewModel.CryptoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var repository: CryptoRepository
    private lateinit var apiService: ApiService
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CryptoAdapter()
        recyclerView.adapter = adapter
        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)

        // Gets remote data and stores it in local database
        cryptoViewModel.setCryptoListFromRemote(cryptoViewModel.getCryptoListFromRemote())

        cryptoViewModel.getCryptoListFromLocal().observe(this, Observer {
            adapter.setData(it)
        })
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.isRefreshing = true
/*
        apiService = ApiBuilder.retrofit.create(
            ApiService::class.java)
        repository = CryptoRepository(apiService)*/

        //getCryptoCoins()
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        //getCryptoCoins()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            swipeRefresh.isRefreshing = true
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /*private fun getCryptoCoins() {
        repository.getCryptoCoins().enqueue(object : Callback<List<ApiResponse>?> {
            override fun onResponse(
                call: Call<List<ApiResponse>?>,
                response: Response<List<ApiResponse>?>
            ) {
                cryptoViewModel.insertCryptoList(response.body()!!)
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<ApiResponse>?>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                // Display cached data
                cryptoViewModel.cryptoList.observe(this@MainActivity, Observer {
                    if (it.isNullOrEmpty()) {
                        displaySnackbar(getString(R.string.snackbar_error_message))
                    } else {
                        displaySnackbar(getString(R.string.snackbar_error_message_cache))
                    }
                })
                Log.d("MainActivity", "onFailure: ${t.localizedMessage}")
            }
        })
    }*/

    private fun displaySnackbar(message: String) {
        Snackbar.make(findViewById(R.id.root), message, Snackbar.LENGTH_LONG).show()
    }
}
