package com.example.rickmorty.di

import com.example.rickmorty.presentation.char_details.CharDetailsFragment
import com.example.rickmorty.presentation.chars_list.CharsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class, ViewModelsModule::class])
interface CharsFragmentComponent {
    fun inject(fragment: CharsListFragment)
    fun inject(fragment: CharDetailsFragment)
}