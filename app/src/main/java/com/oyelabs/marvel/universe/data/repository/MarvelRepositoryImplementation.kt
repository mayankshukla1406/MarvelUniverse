package com.oyelabs.marvel.universe.data.repository

import com.oyelabs.marvel.universe.data.data_source.dto.CharacterDTO.CharacterDTO
import com.oyelabs.marvel.universe.data.data_source.dto.CharactersDTO.CharactersDTO
import com.oyelabs.marvel.universe.data.data_source.dto.MarvelApi
import com.oyelabs.marvel.universe.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val api : MarvelApi
) :MarvelRepository{
    override suspend fun getAllCharacters(offset:Int): CharactersDTO {
        return api.getAllCharacters(offset=offset.toString())
    }

    override suspend fun getAllSearchedCharacters(search: String): CharactersDTO {
        return api.getAllSearchedCharacters(search=search)
    }

    override suspend fun getCharacterById(id: String): CharacterDTO {
        return api.getCharacterById(id)
    }
}