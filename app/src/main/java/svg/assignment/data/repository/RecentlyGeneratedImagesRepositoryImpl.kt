package svg.assignment.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import svg.assignment.core.RecentlyGeneratedImagesLruCache
import svg.assignment.domain.repository.RecentlyGeneratedImagesRepository
import svg.assignment.domain.repository.image

class RecentlyGeneratedImagesRepositoryImpl(
    private val cache: RecentlyGeneratedImagesLruCache
):RecentlyGeneratedImagesRepository {
    override suspend fun getRecentlyGeneratedImages(): Flow<List<image>> = flow {
        val list = cache.getCacheList()
        emit(list.list)
    }

    override suspend fun clearAllImages() : Flow<Boolean> = flow {
        cache.clearCache()
        emit(true)
    }
}