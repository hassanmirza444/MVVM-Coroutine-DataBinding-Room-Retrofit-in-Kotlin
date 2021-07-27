package com.hssn_mirza.digitify_code_challenge.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntegerListConverter {
    @TypeConverter
    fun toString(value: List<Integer>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<Integer>>() {}.type
        if (value != null) {
            return gson.toJson(value, type)
        } else {
            return gson.toJson(ArrayList<Integer>(), type)
        }
    }

    @TypeConverter
    fun toList(value: String): List<Integer> {
        val gson = Gson()
        val type = object : TypeToken<List<Integer>>() {}.type
        return gson.fromJson(value, type)
    }


}