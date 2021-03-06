package com.project.segunfrancis.crypto_info.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.crypto_info.R
import com.project.segunfrancis.crypto_info.model.BaseResponse
import com.project.segunfrancis.crypto_info.util.adapter.CryptoAdapter
import com.project.segunfrancis.crypto_info.util.ResourceState
import com.project.segunfrancis.crypto_info.viewModel.CryptoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    CryptoAdapter.OnCryptoItemClickListener {
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)

        cryptoViewModel.isRefreshing.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                swipeRefresh.isRefreshing = it
            }
        })
        loadDataToView()
        cryptoViewModel.responseMessage.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                when (it.status) {
                    ResourceState.LOADING -> {
                        swipeRefresh.isRefreshing = true
                    }
                    ResourceState.SUCCESS -> {
                        swipeRefresh.isRefreshing = false
                        displaySnackbar(it.message!!)
                    }
                    ResourceState.ERROR -> {
                        swipeRefresh.isRefreshing = false
                        displaySnackbar(it.message!!)
                    }
                }
            }
        })
        swipeRefresh.setOnRefreshListener(this)
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        cryptoViewModel.getCryptoListFromRemote()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cryptoViewModel.searchCoins(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            cryptoViewModel.getCryptoListFromRemote()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(item: BaseResponse) {
        val cryptoDetailFragment = CryptoDetailFragment()
        cryptoDetailFragment.arguments = bundleOf(Pair("item", item))
        cryptoDetailFragment.show(supportFragmentManager, "CryptoDetailFragment")
    }

    private fun displaySnackbar(message: String) {
        Snackbar.make(findViewById(R.id.root), message, Snackbar.LENGTH_LONG).show()
    }

    private fun loadDataToView() {
        val adapter = CryptoAdapter(this)
        // Gets remote data and stores it in local database
        cryptoViewModel.getCryptoListFromLocal().observe(this, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }
}
