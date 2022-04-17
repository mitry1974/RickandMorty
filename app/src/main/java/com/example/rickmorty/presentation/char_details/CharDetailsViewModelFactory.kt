package com.example.rickmorty.presentation.char_details

import com.example.rickmorty.domain.models.Character
import dagger.assisted.AssistedFactory

@AssistedFactory
interface CharDetailsViewModelFactory {
    fun create(char: Character): CharDetailsViewModel
}