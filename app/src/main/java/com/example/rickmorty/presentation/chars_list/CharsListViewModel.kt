package com.example.rickmorty.presentation.chars_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickmorty.data.mappers.ResponseToChar
import com.example.rickmorty.domain.CharsListRepository
import com.example.rickmorty.domain.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharsListViewModel @Inject constructor(private val charsListRepository: CharsListRepository) :
    ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    val flow: Flow<PagingData<Character>> =
        charsListRepository.getCharacters().map { pd ->
            pd.map { ResponseToChar.toVO(it) }
        }
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)

}
