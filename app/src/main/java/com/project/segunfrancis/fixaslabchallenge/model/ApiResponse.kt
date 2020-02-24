package com.project.segunfrancis.fixaslabchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by SegunFrancis
 */

@Entity(tableName = "crypto_table")
data class ApiResponse(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("price_usd") val priceUsd: String,
    @SerializedName("percent_change_1h") val percentageChange1Hour: String
): Serializable