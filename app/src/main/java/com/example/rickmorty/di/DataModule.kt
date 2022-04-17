package com.example.rickmorty.di

import com.example.rickmorty.data.api.RAMService
import com.example.rickmorty.data.repository.characters_list.CharsListRepositoryImpl
import com.example.rickmorty.domain.CharsListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun providesCharactersListRepository(remote: RAMService): CharsListRepository =
        CharsListRepositoryImpl(remote)
}