package com.shadi.com.practisetest.network

import com.shadi.com.practisetest.models.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface will contain all the webservice calls
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
interface RestAPIInterface {

    @GET("/api/")
    fun getUsers(@Query("results") results: String): Call<UserData>;

}