package com.luffy18346.amexdemo.data.di

import com.luffy18346.amexdemo.data.Endpoints
import com.luffy18346.amexdemo.data.PicsumPhotosApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<PicsumPhotosApi> {
        get<Retrofit>().create(PicsumPhotosApi::class.java)
    }

}