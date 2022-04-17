package com.example.rickmorty.di

import com.example.rickmorty.data.api.RAMService
import com.example.rickmorty.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesRAMRetrofitService(): RAMService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL_RAM_API)
            .build()
            .create(RAMService::class.java)
    }
}