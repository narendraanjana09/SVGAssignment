package svg.assignment.domain.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import svg.assignment.core.Resource

typealias image = String

interface GenerateImageRepository {

    suspend fun generateRandomImage(
        coroutineScope: CoroutineScope
    ) : Flow<Resource<image>>

}