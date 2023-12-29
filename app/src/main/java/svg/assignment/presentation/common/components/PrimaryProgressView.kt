package svg.assignment.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryProgressView(
  text: String = "Loading...",
  modifier: Modifier=Modifier,
  backgroundColor: Color = MaterialTheme.colorScheme.background,
  contentColor: Color = MaterialTheme.colorScheme.onBackground
) {
  Box(modifier = Modifier
    .fillMaxSize()
    .background(
      color = MaterialTheme.colorScheme.onBackground.copy(
        alpha = 0.3f
      )
    ).clickable(enabled = false, onClickLabel = "", onClick = {})
    .padding(horizontal = 20.dp),
    contentAlignment = Alignment.Center
  ) {
    Card(
      modifier = modifier,
      shape = RoundedCornerShape(10.dp),
      colors = CardDefaults.cardColors(
        containerColor = backgroundColor,
        contentColor = contentColor
      ),
      elevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp
      )
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
      ) {
        SpinningProgress(
          modifier = Modifier.size(24.dp),
        )
        Text(text = text, color = contentColor)
      }
    }
  }
}