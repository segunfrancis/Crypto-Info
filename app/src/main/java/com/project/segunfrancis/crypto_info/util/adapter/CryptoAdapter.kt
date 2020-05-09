package com.project.segunfrancis.crypto_info.util.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.segunfrancis.crypto_info.R
import com.project.segunfrancis.crypto_info.model.BaseResponse
import kotlinx.android.synthetic.main.crypto_coin_list_item.view.*

/**
 * Created by SegunFrancis
 */

class CryptoAdapter(private val listener: OnCryptoItemClickListener) :
    ListAdapter<BaseResponse, CryptoAdapter.CryptoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.crypto_coin_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface OnCryptoItemClickListener {
        fun onClick(item: BaseResponse)
    }

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: BaseResponse?, listener: OnCryptoItemClickListener) = with(itemView) {
            coin_name.text = item?.name
            coin_price.text = "$".plus(String.format("%.5f", item?.quote?.USD?.price))
            coin_symbol.text = item?.symbol
            when {
                item!!.quote.USD.percent_change_1h < 0 -> {
                    coin_percent_change.text =  "${String.format("%.4f", item.quote.USD.percent_change_1h)}%"
                    coin_percent_change.setTextColor(ContextCompat.getColor(context, R.color.textRed))
                }
                item.quote.USD.percent_change_1h > 0 -> {
                    coin_percent_change.text = "${String.format("%.4f", item.quote.USD.percent_change_1h)}%"
                    coin_percent_change.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                }
                else -> coin_percent_change.text = "${String.format("%.4f", item.quote.USD.percent_change_1h)}%"
            }
            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BaseResponse>() {
        override fun areItemsTheSame(oldItem: BaseResponse, newItem: BaseResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseResponse, newItem: BaseResponse): Boolean {
            return oldItem.equals(newItem)
        }
    }
}