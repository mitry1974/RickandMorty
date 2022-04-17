package com.example.rickmorty.data.repository.characters_list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickmorty.data.api.CharacterResponse
import com.example.rickmorty.data.api.RAMService
import com.example.rickmorty.domain.CharsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharsListRepositoryImpl @Inject constructor(
    private val movieService: RAMService
) : CharsListRepository {
    override fun getCharacters(): Flow<PagingData<CharacterResponse>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false)
        ) {
            CharsPagingSource(service = movieService)
        }.flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 25
    }
}