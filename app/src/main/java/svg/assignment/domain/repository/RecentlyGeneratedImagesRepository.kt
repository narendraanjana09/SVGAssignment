package svg.assignment.domain.repository

import kotlinx.coroutines.flow.Flow

interface RecentlyGeneratedImagesRepository {

    suspend fun getRecentlyGeneratedImages(
    ) : Flow<List<image>>

    suspend fun clearAllImages(
    ) : Flow<Boolean>

}