package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.segunfrancis.fixaslabchallenge.model.Quote

/**
 * Created by SegunFrancis
 */
class Converters {
    /*@TypeConverter
    fun fromStringToPlatform(value: String?): Platform? {
        val type = object : TypeToken<Platform>() {}.type
        return Gson().fromJson<Platform>(value, type)
    }

    @TypeConverter
    fun fromPlatformToString(platform: Platform?): String? {
        return Gson().toJson(platform)
    }*/

    @TypeConverter
    fun fromStringToQuote(value: String?): Quote? {
        val type = object : TypeToken<Quote>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromQuoteToString(quote: Quote?): String? {
        return Gson().toJson(quote)
    }
}