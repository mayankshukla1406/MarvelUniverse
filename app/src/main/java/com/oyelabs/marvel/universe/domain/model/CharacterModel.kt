package com.oyelabs.marvel.universe.domain.model

import com.oyelabs.marvel.universe.data.data_source.dto.CharacterDTO.Comics

data class CharacterModel(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt: String,
    val comics : List<String>,
)
