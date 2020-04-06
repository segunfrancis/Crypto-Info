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
import com.project.segunfrancis.fixaslabchallenge.util.ResourceState
import com.project.segunfrancis.fixaslabchallenge.viewModel.CryptoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh.isRefreshing = true

        val adapter = CryptoAdapter()
        recyclerView.adapter = adapter
        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)

        // Gets remote data and stores it in local database
        cryptoViewModel.getCryptoListFromRemote()

        cryptoViewModel.getCryptoListFromLocal().observe(this, Observer {
            //adapter.setData(it)
            swipeRefresh.isRefreshing = false
        })
        swipeRefresh.setOnRefreshListener(this)

        cryptoViewModel.responseData.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> swipeRefresh.isRefreshing = true
                ResourceState.SUCCESS -> {
                    displaySnackbar(it.message!!)
                    adapter.setData(it.data?.data)
                    swipeRefresh.isRefreshing = false
                }
                ResourceState.ERROR -> {
                    displaySnackbar(it.message!!)
                    Log.e("MainActivity", "Error loading data", it.throwable)
                    swipeRefresh.isRefreshing = false
                }
            }
        })
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        cryptoViewModel.getCryptoListFromRemote()
        swipeRefresh.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            //swipeRefresh.isRefreshing = true
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displaySnackbar(message: String) {
        Snackbar.make(findViewById(R.id.root), message, Snackbar.LENGTH_LONG).show()
    }
}
