package com.shadi.com.practisetest.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shadi.com.practisetest.models.*


/**
 * This is a converter class which helps in converting pojo to string and
 * vice-versa for purpose of saving & retrieving data from database
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
class Converters {


    @TypeConverter
    fun toLocations(value: String?): Location? {
        if (value.isNullOrEmpty()) {
            return Location()
        }
        val gson = Gson()
        val type = object : TypeToken<Location>() {}.type
        return gson.fromJson(value, type)
    }


    @TypeConverter
    fun fromLocations(value: Location?): String? {
        val gson = Gson()
        val type = object : TypeToken<Location>() {}.type
        return gson.toJson(value, type)
    }


    @TypeConverter
    fun toIds(value: String?): Id? {
        if (value.isNullOrEmpty()) {
            return Id()
        }
        val gson = Gson()
        val type = object : TypeToken<Id>() {}.type
        return gson.fromJson(value, type)
    }



    @TypeConverter
    fun fromIds(value: Id?): String? {
        val gson = Gson()
        val type = object : TypeToken<Id>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toDobs(value: String?): Dob? {
        if (value.isNullOrEmpty()) {
            return Dob()
        }
        val gson = Gson()
        val type = object : TypeToken<Dob>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromDobs(value: Dob?): String? {
        val gson = Gson()
        val type = object : TypeToken<Dob>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRegistered(value: String?): Registered? {
        if (value.isNullOrEmpty()) {
            return Registered()
        }
        val gson = Gson()
        val type = object : TypeToken<Registered>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromRegistered(value: Registered?): String? {
        val gson = Gson()
        val type = object : TypeToken<Registered>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toName(value: String?): Name? {
        if (value.isNullOrEmpty()) {
            return Name()
        }
        val gson = Gson()
        val type = object : TypeToken<Name>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromName(value: Name?): String? {
        val gson = Gson()
        val type = object : TypeToken<Name>() {}.type
        return gson.toJson(value, type)
    }
}