package com.a4nt0n64r.contacts.database.remote

import com.a4nt0n64r.contacts.BuildConfig
import com.a4nt0n64r.contacts.models.UserDataFromApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("?results=20")
    fun getUsersFromCloud(): Call<UserDataFromApi>

    companion object {
        operator fun invoke(): ApiService {
            val gson: Gson = GsonBuilder().create()

            val interceptor = HttpLoggingInterceptor().also {
                if (BuildConfig.DEBUG) {
                    it.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    it.level = HttpLoggingInterceptor.Level.NONE
                }
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)

            return retrofit
        }
    }

}
