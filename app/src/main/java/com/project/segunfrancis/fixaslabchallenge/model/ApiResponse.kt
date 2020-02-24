package com.project.segunfrancis.fixaslabchallenge.model

import com.google.gson.annotations.SerializedName

/**
 * Created by SegunFrancis
 */

data class ApiResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("price_usd") val priceUsd: String,
    @SerializedName("percent_change_1h") val percentageChange1Hour: String
)