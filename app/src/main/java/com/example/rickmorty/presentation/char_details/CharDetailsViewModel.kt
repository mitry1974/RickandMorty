package com.example.rickmorty.presentation.char_details

import androidx.lifecycle.ViewModel
import com.example.rickmorty.domain.models.Character
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class CharDetailsViewModel @AssistedInject constructor(@Assisted val char: Character):ViewModel()