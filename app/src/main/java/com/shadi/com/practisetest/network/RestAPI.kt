package com.shadi.com.practisetest.network

import androidx.lifecycle.MutableLiveData
import com.shadi.com.practisetest.Constants
import com.shadi.com.practisetest.models.UserData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This class will be used for the configuration setup of retrofit & listing of all the apis called for this app
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
class RestAPI {

    private var restAPIInterface: RestAPIInterface? = null
    var mutableUserData: MutableLiveData<UserData>

    init {
        mutableUserData = MutableLiveData<UserData>()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        restAPIInterface = retrofit.create(RestAPIInterface::class.java)

    }

    /* Method is used to get data from server*/
    fun getUsers(results: String) {
        restAPIInterface?.getUsers(results)?.enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                mutableUserData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                t.printStackTrace()
                mutableUserData.postValue(null)
            }
        })
    }


}