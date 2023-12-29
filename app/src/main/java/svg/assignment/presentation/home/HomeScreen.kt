package svg.assignment.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import svg.assignment.R
import svg.assignment.presentation.common.components.PrimaryButton

@Composable
fun HomeScreen(
    navigateToGenerateImageScreen: () -> Unit,
    navigateToRecentsScreen: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.headline_home),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(200.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PrimaryButton(text = stringResource(id = R.string.generate_dogs)) {
                navigateToGenerateImageScreen()
            }
            PrimaryButton(text = stringResource(id = R.string.recent_generated)) {
                navigateToRecentsScreen()
            }
        }
    }
}