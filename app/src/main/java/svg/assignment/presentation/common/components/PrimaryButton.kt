package svg.assignment.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text:String,
    onClick:() -> Unit
) {
    Button(
        onClick = onClick,
        border = BorderStroke(
            width = (1.5).dp,
            color = Color.Black
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(red = 66, green = 134, blue = 244),
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}