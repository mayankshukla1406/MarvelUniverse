package com.oyelabs.marvel.universe.ui.CharactersList

import com.oyelabs.marvel.universe.domain.model.CharacterModel

data class MarvelListState(
    val isLoading : Boolean = false,
    val charactersList : List<CharacterModel> = emptyList(),
    val error : String = ""
)