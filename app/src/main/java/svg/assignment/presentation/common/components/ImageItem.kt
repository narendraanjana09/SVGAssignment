package svg.assignment.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers


@Composable
fun ImageItem(
    modifier: Modifier,
    imageLink: String?
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(
                imageLink
            )
            .dispatcher(Dispatchers.IO)
            .memoryCacheKey(imageLink)
            .diskCacheKey(imageLink)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        when(painter.state){
            is AsyncImagePainter.State.Loading -> {
                SpinningProgress()
            }
            else -> {}
        }
    }
}