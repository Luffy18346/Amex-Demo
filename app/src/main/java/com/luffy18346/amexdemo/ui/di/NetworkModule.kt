package com.luffy18346.amexdemo.ui.di

import com.luffy18346.amexdemo.BuildConfig
import com.luffy18346.amexdemo.data.PicsumPhotosApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
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

//    // Multiple retrofit instances with different names and different base urls...
//    single<Retrofit>(named("Retrofit2")) {
//        Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create(get()))
//            .client(get())
//            .build()
//    }

    single<Retrofit>/*(named("Retrofit1"))*/ {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<PicsumPhotosApi> {
        get<Retrofit>().create(PicsumPhotosApi::class.java)
    }

}