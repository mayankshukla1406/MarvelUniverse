package com.oyelabs.marvel.universe.data.data_source.dto.CharacterDTO

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)