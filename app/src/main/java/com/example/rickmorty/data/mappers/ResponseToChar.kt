package com.example.rickmorty.data.mappers

import com.example.rickmorty.data.api.CharacterResponse
import com.example.rickmorty.domain.models.Character

object ResponseToChar {
    fun toVO(dto: CharacterResponse): Character = Character(
        id = dto.id ?: 0,
        name = dto.name.orEmpty(),
        status = dto.status.orEmpty(),
        species = dto.species.orEmpty(),
        gender = dto.gender.orEmpty(),
        image = dto.image.orEmpty(),
        location = dto.location.name.orEmpty(),
        episodes = dto.episode ?: emptyList()
    )
}