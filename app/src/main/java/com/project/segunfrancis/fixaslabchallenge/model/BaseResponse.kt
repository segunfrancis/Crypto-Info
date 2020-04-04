package com.project.segunfrancis.fixaslabchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_table")
data class BaseResponse(
    @PrimaryKey
    val id: Int,
    val circulating_supply: Double,
    val cmc_rank: Int,
    val date_added: String,
    val last_updated: String,
    //val max_supply: Any,
    val name: String,
    val num_market_pairs: Int,
    //val platform: Platform,
    val quote: Quote,
    val slug: String,
    val symbol: String
    //val tags: List<Any>,
    //val total_supply: Int
)