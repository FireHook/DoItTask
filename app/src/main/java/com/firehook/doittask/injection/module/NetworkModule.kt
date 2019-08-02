package com.firehook.doittask.injection.module

import com.firehook.doittask.network.retrofit.DoItService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

@Module
class NetworkModule {

    @Provides @Singleton fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides @Singleton fun provideBulletinService(okHttpClient: OkHttpClient): DoItService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://testapi.doitserver.in.ua/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(DoItService::class.java)
    }
}