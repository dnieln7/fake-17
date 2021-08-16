package com.dnieln7.fake17.data.database.converter

import androidx.room.TypeConverter
import com.dnieln7.fake17.domain.CatImage
import com.dnieln7.fake17.domain.CatWeight
import com.google.gson.Gson

class CatConverters {
    @TypeConverter
    fun fromImage(value: CatImage): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toImage(value: String): CatImage {
        return Gson().fromJson(value, CatImage::class.java)
    }

    @TypeConverter
    fun fromWeight(value: CatWeight): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWeight(value: String): CatWeight {
        return Gson().fromJson(value, CatWeight::class.java)
    }
}