package svg.assignment.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import svg.assignment.core.RecentlyGeneratedImagesLruCache
import svg.assignment.data.remote.GenerateRandomImageAPI
import svg.assignment.data.repository.GenerateImageRepositoryImpl
import svg.assignment.data.repository.RecentlyGeneratedImagesRepositoryImpl
import svg.assignment.domain.repository.GenerateImageRepository
import svg.assignment.domain.repository.RecentlyGeneratedImagesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGenerateRandomImageApi(): GenerateRandomImageAPI {
        return Retrofit.Builder()
            .baseUrl(GenerateRandomImageAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GenerateRandomImageAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRecentlyGeneratedImagesLruCache(
        application: Application
    ): RecentlyGeneratedImagesLruCache {
        return RecentlyGeneratedImagesLruCache(application)
    }

    @Provides
    @Singleton
    fun provideGenerateImageRepository(
        api: GenerateRandomImageAPI,
        cache: RecentlyGeneratedImagesLruCache
    ) : GenerateImageRepository = GenerateImageRepositoryImpl(api,cache)

    @Provides
    @Singleton
    fun provideRecentlyGenerateImagesRepository(
        cache: RecentlyGeneratedImagesLruCache
    ) : RecentlyGeneratedImagesRepository = RecentlyGeneratedImagesRepositoryImpl(cache)
}