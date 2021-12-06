package com.oyelabs.marvel.universe.domain.use_cases

import com.oyelabs.marvel.universe.domain.model.CharacterModel
import com.oyelabs.marvel.universe.domain.repository.MarvelRepository
import com.oyelabs.marvel.universe.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCharacterCase @Inject constructor(
private val repository : MarvelRepository
) {
    operator fun invoke(search:String): Flow<State<List<CharacterModel>>> = flow {
        try {
            emit(State.Loading<List<CharacterModel>>())
            val list = repository.getAllSearchedCharacters(search).data.results.map {
                it.toCharacter()
            }
            emit(State.Success<List<CharacterModel>>(list))
        }
        catch (e: HttpException){
            emit(State.Error<List<CharacterModel>>(e.printStackTrace().toString()))
        }
        catch (e: IOException){
            emit(State.Error<List<CharacterModel>>(e.printStackTrace().toString()))
        }
    }
}