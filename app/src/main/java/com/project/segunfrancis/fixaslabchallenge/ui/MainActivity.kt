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

        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        cryptoViewModel.getCryptoListFromRemote()
        loadDataToView()
        swipeRefresh.setOnRefreshListener(this)
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        loadDataToView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            loadDataToView()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displaySnackbar(message: String) {
        Snackbar.make(findViewById(R.id.root), message, Snackbar.LENGTH_LONG).show()
    }

    private fun loadDataToView() {
        val adapter = CryptoAdapter()
        recyclerView.adapter = adapter
        // Gets remote data and stores it in local database
        cryptoViewModel.getCryptoListFromLocal().observe(this, Observer {
            when (it?.status) {
                ResourceState.LOADING -> swipeRefresh.isRefreshing = true
                ResourceState.SUCCESS -> {
                    displaySnackbar(it.message!!)
                    adapter.setData(it.data)
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
}
