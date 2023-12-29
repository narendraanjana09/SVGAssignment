package svg.assignment.presentation.recentlyGeneratedImages

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import svg.assignment.R
import svg.assignment.presentation.common.components.ImageItem
import svg.assignment.presentation.common.components.PrimaryButton
import svg.assignment.presentation.common.components.PrimaryTopBar
import svg.assignment.presentation.common.components.drawHorizontalScrollbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyGenerateImageScreen(
    navigateBack: () -> Unit,
    viewModel: RecentlyGeneratedImagesViewModel = hiltViewModel()
){

    val recentlyGeneratedImages = viewModel.generatedImages
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = Unit){
        viewModel.getAllRecentlyGeneratedImages()
    }

    Scaffold(
        topBar = {
            PrimaryTopBar(title = stringResource(id = R.string.recent_generated)) {
                navigateBack()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(vertical = 20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .drawHorizontalScrollbar(scrollState)
                   .horizontalScroll(scrollState)
                   .height(400.dp),
               horizontalArrangement = Arrangement.spacedBy(10.dp)
           ){
               recentlyGeneratedImages.forEach { image->
                   ImageItem(
                       modifier =  Modifier
                           .size(400.dp),
                       imageLink = image
                   )
               }
           }

            PrimaryButton(text = stringResource(id = R.string.clear_dogs)) {
                viewModel.clearAllImages()
            }
        }
    }

}