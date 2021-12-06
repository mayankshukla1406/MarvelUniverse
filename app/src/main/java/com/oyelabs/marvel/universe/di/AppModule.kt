package com.oyelabs.marvel.universe.di

import com.oyelabs.marvel.universe.data.data_source.dto.MarvelApi
import com.oyelabs.marvel.universe.data.repository.MarvelRepositoryImplementation
import com.oyelabs.marvel.universe.domain.repository.MarvelRepository
import com.oyelabs.marvel.universe.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMarvelApi():MarvelApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api:MarvelApi):MarvelRepository{
        return MarvelRepositoryImplementation(api)
    }
}
