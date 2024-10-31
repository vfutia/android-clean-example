package com.vfutia.data.di

import android.content.Context
import androidx.room.Room
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.vfutia.data.ListRepositoryImpl
import com.vfutia.data.db.AppDatabase
import com.vfutia.data.db.ListDataDao
import com.vfutia.data.db.RoomDataSource
import com.vfutia.data.network.RestDataSource
import com.vfutia.domain.DataSource
import com.vfutia.domain.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Named("network")
    internal fun provideService(): DataSource {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val objectMapper = ObjectMapper()
            .registerKotlinModule()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)

        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(client)
            .build()
            .create(RestDataSource::class.java)
    }

    @Provides
    internal fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    internal fun provideListDataDao(database: AppDatabase): ListDataDao {
        return database.listDataDao()
    }

    @Provides
    @Named("persistence")
    internal fun provideDataSource(listDataDao: ListDataDao): DataSource = RoomDataSource(listDataDao)

    @Provides
    fun provideRepository(
        @Named("persistence") persistence: DataSource,
        @Named("network") network: DataSource
    ): ListRepository = ListRepositoryImpl(persistence, network)
}