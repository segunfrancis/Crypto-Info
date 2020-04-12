package com.project.segunfrancis.fixaslabchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.fixaslabchallenge.R
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.util.adapter.CryptoAdapter
import com.project.segunfrancis.fixaslabchallenge.util.ResourceState
import com.project.segunfrancis.fixaslabchallenge.viewModel.CryptoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    CryptoAdapter.OnCryptoItemClickListener {
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh.isRefreshing = true

        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        cryptoViewModel.getCryptoListFromRemote()
        loadDataToView()
        cryptoViewModel.responseMessage.observe(this, Observer {
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
        val adapter =
            CryptoAdapter(this)
        recyclerView.adapter = adapter
        // Gets remote data and stores it in local database
        cryptoViewModel.getCryptoListFromLocal().observe(this, Observer {
            adapter.setData(it)
        })
    }
}
