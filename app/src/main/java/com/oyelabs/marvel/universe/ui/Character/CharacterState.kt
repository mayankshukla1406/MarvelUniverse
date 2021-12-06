package com.oyelabs.marvel.universe.ui.Character

import com.oyelabs.marvel.universe.domain.model.CharacterModel

data class CharacterState(
    val isLoading : Boolean = false,
    val characterDetail : List<CharacterModel> = emptyList(),
    val error : String = ""
)