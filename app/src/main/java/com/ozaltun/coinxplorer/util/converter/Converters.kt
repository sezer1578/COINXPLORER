package com.ozaltun.coinxplorer.util.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ozaltun.coinxplorer.data.remote.dto.Description
import com.ozaltun.coinxplorer.data.remote.dto.Image

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromDescription(description: Description): String {
        return gson.toJson(description)
    }

    @TypeConverter
    fun toDescription(json: String): Description {
        return gson.fromJson(json, Description::class.java)
    }
    @TypeConverter
    fun fromImage(image: Image): String {
        return gson.toJson(image)
    }

    @TypeConverter
    fun toImage(json: String): Image {
        return gson.fromJson(json, Image::class.java)
    }
}