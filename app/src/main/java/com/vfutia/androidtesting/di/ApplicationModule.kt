package com.vfutia.androidtesting.di

import com.vfutia.data.di.DataModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [
    DataModule::class
])
@InstallIn(SingletonComponent::class)
class ApplicationModule