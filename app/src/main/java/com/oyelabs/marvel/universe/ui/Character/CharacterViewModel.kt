package com.oyelabs.marvel.universe.ui.Character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyelabs.marvel.universe.domain.use_cases.CharacterUseCase
import com.oyelabs.marvel.universe.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) :ViewModel(){

    private val characterValue = MutableStateFlow(CharacterState())
    val _characterValue : StateFlow<CharacterState> = characterValue

    fun getCharacterByIdValue(id:String)=viewModelScope.launch{
        characterUseCase(id).collect {
            when(it){
                is State.Success-> {
                   characterValue.value = CharacterState(
                       characterDetail = it.data?: emptyList()
                   )
                }
                is State.Loading->{
                    characterValue.value = CharacterState(isLoading = true)
                }
                is State.Error->{
                    characterValue.value = CharacterState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}