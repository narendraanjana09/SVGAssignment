package svg.assignment.data.remote

import retrofit2.http.GET
import svg.assignment.data.remote.dto.GeneratedImageDto

interface GenerateRandomImageAPI {

    @GET("image/random")
    suspend fun generateRandomImage():GeneratedImageDto

    companion object {
        const val BASE_URL = "https://dog.ceo/api/breeds/"
    }
}