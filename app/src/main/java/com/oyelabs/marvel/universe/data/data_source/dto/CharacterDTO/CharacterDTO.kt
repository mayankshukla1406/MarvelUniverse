package com.oyelabs.marvel.universe.data.data_source.dto.CharacterDTO

import com.oyelabs.marvel.universe.domain.model.CharacterModel

data class CharacterDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)