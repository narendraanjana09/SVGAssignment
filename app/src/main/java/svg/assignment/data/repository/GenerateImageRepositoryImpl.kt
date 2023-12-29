package svg.assignment.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import svg.assignment.core.Constants
import svg.assignment.core.RecentlyGeneratedImagesLruCache
import svg.assignment.core.Resource
import svg.assignment.data.remote.GenerateRandomImageAPI
import svg.assignment.domain.repository.GenerateImageRepository
import java.io.IOException


class GenerateImageRepositoryImpl(
    private val api: GenerateRandomImageAPI,
    private val cache: RecentlyGeneratedImagesLruCache
):GenerateImageRepository {

    override suspend fun generateRandomImage(
        coroutineScope: CoroutineScope
    )= callbackFlow {
        try {
            val result = api.generateRandomImage()
            cache.addImageToCache(result.message)
            trySend(Resource.Success(data = result.message))
        } catch(e: HttpException) {
            trySend(Resource.Error(message = Constants.NOT_FOUND))
        } catch(e: IOException) {
            trySend(Resource.Error(message = Constants.NETWORK_ERROR))
        } catch (e: Exception) {
            trySend(Resource.Error(message = e.message.toString()))
        } finally {
            close()
        }
        awaitClose { }
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), initialValue = Resource.Loading(message = "Generating Image..."))
}