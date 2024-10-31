package com.vfutia.androidtesting.di

import android.content.Context
import androidx.room.Room
import com.vfutia.androidtesting.data.AppDatabase
import com.vfutia.androidtesting.data.DataSourceImpl
import com.vfutia.androidtesting.data.ListDataDao
import com.vfutia.data.ListRepositoryImpl
import com.vfutia.domain.DataSource
import com.vfutia.domain.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideListDataDao(database: AppDatabase): ListDataDao {
        return database.listDataDao()
    }

    @Provides
    fun provideDataSource(listDataDao: ListDataDao): DataSource = DataSourceImpl(listDataDao)

    @Provides
    fun provideRepository(dataSource: DataSource): ListRepository = ListRepositoryImpl(dataSource)
}