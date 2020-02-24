package com.project.segunfrancis.fixaslabchallenge.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.segunfrancis.fixaslabchallenge.R
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import kotlinx.android.synthetic.main.crypto_coin_list_item.view.*
import java.util.*

/**
 * Created by SegunFrancis
 */

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    private var coinList: List<ApiResponse>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.crypto_coin_list_item, parent, false)
        )
    }

    override fun getItemCount() = coinList!!.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) =
        holder.bind(coinList?.get(position))

    fun setData(coinList: List<ApiResponse>?) {
        this.coinList = coinList
        notifyDataSetChanged()
    }

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ApiResponse?) = with(itemView) {
            coin_name.text = item?.name
            coin_price.text = "$".plus(item?.priceUsd)
            coin_symbol.text = item?.symbol
            when {
                item?.percentageChange1Hour!!.toDouble() < 0 -> {
                    coin_percent_change.text = item.percentageChange1Hour.plus("%")
                    coin_percent_change.setTextColor(resources.getColor(R.color.textRed))
                }
                item.percentageChange1Hour.toDouble() > 0 -> {
                    coin_percent_change.text = item.percentageChange1Hour.plus("%")
                    coin_percent_change.setTextColor(resources.getColor(R.color.colorAccent))
                }
                else -> coin_percent_change.text = item.percentageChange1Hour.plus("%")
            }
        }
    }
}