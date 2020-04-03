package com.project.segunfrancis.fixaslabchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by SegunFrancis
 */

@Entity(tableName = "crypto_table")
data class ApiResponse(
    @PrimaryKey @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("symbol") var symbol: String,
    @SerializedName("price_usd") var priceUsd: String,
    @SerializedName("percent_change_1h") var percentageChange1Hour: String
)