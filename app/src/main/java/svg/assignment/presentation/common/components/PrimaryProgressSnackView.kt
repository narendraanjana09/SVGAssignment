package svg.assignment.presentation.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import svg.assignment.presentation.common.model.ScreenState

@Composable
fun PrimaryProgressSnackView(
  screenState: ScreenState
) {
  screenState.message.let {
    if (it.second) {
      when (val first = it.first) {
        is String -> PrimarySnackBar(text = first.toString())
        is Int -> PrimarySnackBar(text = stringResource(id = first))
      }
    }
  }
  screenState.loading.let {
    if (it.second) {
      when (val first = it.first) {
        is String -> PrimaryProgressView(text = first.toString())
        is Int -> PrimaryProgressView(text = stringResource(id = first))
      }
    }
  }
}