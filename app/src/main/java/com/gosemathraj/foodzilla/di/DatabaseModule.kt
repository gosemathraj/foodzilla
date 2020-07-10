package com.gosemathraj.foodzilla.di

import android.content.Context
import androidx.room.Room
import com.gosemathraj.foodzilla.data.local.db.FoodzillaDao
import com.gosemathraj.foodzilla.data.local.db.FoodzillaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    lateinit var foodzillaDatabase: FoodzillaDatabase

    @Provides
    @Singleton
    fun providesFoodzillaDatabase(@ApplicationContext context : Context) : FoodzillaDatabase {
        foodzillaDatabase = Room.databaseBuilder(context,
                            FoodzillaDatabase::class.java, "foodzilla.db"
                            ).build()
        return foodzillaDatabase
    }

    @Provides
    @Singleton
    fun providesFoodzillaDao(foodzillaDatabase: FoodzillaDatabase) : FoodzillaDao {
        return foodzillaDatabase.foodzillaDao()
    }
}