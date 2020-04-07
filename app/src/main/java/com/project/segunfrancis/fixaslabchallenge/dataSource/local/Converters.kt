package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.segunfrancis.fixaslabchallenge.model.Quote

/**
 * Created by SegunFrancis
 */
class Converters {

    @TypeConverter
    fun fromQuoteToString(quote: Quote?): String? {
        return Gson().toJson(quote)
    }

    @TypeConverter
    fun fromStringToQuote(value: String?): Quote? {
        val type = object : TypeToken<Quote>() {}.type
        return Gson().fromJson(value, type)
    }
}