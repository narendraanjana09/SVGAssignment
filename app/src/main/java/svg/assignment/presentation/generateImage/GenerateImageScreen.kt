package svg.assignment.presentation.generateImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import svg.assignment.R
import svg.assignment.presentation.common.components.ImageItem
import svg.assignment.presentation.common.components.PrimaryButton
import svg.assignment.presentation.common.components.PrimaryProgressSnackView
import svg.assignment.presentation.common.components.PrimaryTopBar
import svg.assignment.presentation.common.components.SpinningProgress

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateImageScreen(
    navigateBack: () -> Unit,
    viewModel: GenerateImageViewModel = hiltViewModel()
){
    val generatedImage by viewModel.generatedImage
    val progressErrorState by viewModel.progressAndErrorState

    Scaffold(
        topBar = {
            PrimaryTopBar(title = stringResource(id = R.string.generate_dogs)) {
                navigateBack()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ImageItem(
                    modifier =  Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    imageLink = generatedImage
                )
                PrimaryButton(text = stringResource(id = R.string.generate)) {
                    viewModel.generateRandomImage()
                }
            }
            PrimaryProgressSnackView(screenState = progressErrorState)
        }
    }
}
