package com.oyelabs.marvel.universe.data.repository

import android.util.Log
import android.widget.Toast
import com.oyelabs.marvel.universe.data.data_source.dto.MarvelApi
import com.oyelabs.marvel.universe.domain.repository.MarvelComicRepository
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MarvelComicRepositoryImpl @Inject constructor(
    private val api : MarvelApi,
) : MarvelComicRepository {
    override fun getComics() {
        Log.d("activity","comic repository")
    }
}