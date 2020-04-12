package com.project.segunfrancis.fixaslabchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "crypto_table")
data class BaseResponse(
    @PrimaryKey
    val id: Int,
    val circulating_supply: Double,
    val cmc_rank: Int,
    val date_added: String,
    val last_updated: String,
    val name: String,
    val num_market_pairs: Int,
    val quote: Quote,
    val slug: String,
    val symbol: String
): Serializable