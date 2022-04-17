package com.example.rickmorty.domain

import androidx.paging.PagingData
import com.example.rickmorty.data.api.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharsListRepository {
    fun getCharacters(): Flow<PagingData<CharacterResponse>>
}
