package com.example.rickmorty.data.repository.characters_list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmorty.data.api.CharacterResponse
import com.example.rickmorty.data.api.RAMService
import com.example.rickmorty.utils.Constants.RAM_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class CharsPagingSource(
    private val service: RAMService
) : PagingSource<Int, CharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val pageIndex = params.key ?: RAM_STARTING_PAGE_INDEX
        return try {
            val response = service.getCharactersList(pageIndex)
            val characters = response.results

            LoadResult.Page(
                data = characters,
                prevKey = if (pageIndex == RAM_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = if (response.info.next == null) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}