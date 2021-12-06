package com.oyelabs.marvel.universe.domain.repository

import com.oyelabs.marvel.universe.data.data_source.dto.CharacterDTO.CharacterDTO
import com.oyelabs.marvel.universe.data.data_source.dto.CharactersDTO.CharactersDTO

interface MarvelRepository {

    suspend fun getAllCharacters(offset:Int):CharactersDTO
    suspend fun getAllSearchedCharacters(search:String):CharactersDTO
    suspend fun getCharacterById(id:String):CharacterDTO
}