package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.Quote
import com.project.segunfrancis.fixaslabchallenge.util.Resource

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

    @TypeConverter
    fun fromResourceToString(resource: Resource<List<BaseResponse>>?): String? {
        return Gson().toJson(resource)
    }

    @TypeConverter
    fun fromStringToResource(value: String?): Resource<List<BaseResponse>?> {
        val type = object : TypeToken<Resource<List<BaseResponse>>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromLivDataToString(liveData: LiveData<Resource<List<BaseResponse>>>?): String? {
        return Gson().toJson(liveData)
    }

    @TypeConverter
    fun fromStringToLiveData(value: String?): LiveData<Resource<List<BaseResponse>>>? {
        val type = object : TypeToken<LiveData<Resource<List<BaseResponse>>>?>() {}.type
        return Gson().fromJson(value, type)
    }
}