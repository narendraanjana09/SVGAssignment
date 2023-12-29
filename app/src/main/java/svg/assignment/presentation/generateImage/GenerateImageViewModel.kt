package svg.assignment.presentation.generateImage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import svg.assignment.core.Constants
import svg.assignment.core.Resource
import svg.assignment.domain.repository.GenerateImageRepository
import svg.assignment.presentation.common.model.ScreenState
import javax.inject.Inject

@HiltViewModel
class GenerateImageViewModel @Inject constructor(
    private val repository: GenerateImageRepository
):ViewModel() {

    private val _progressAndErrorState = mutableStateOf(ScreenState())
    val progressAndErrorState: State<ScreenState> = _progressAndErrorState

    private val _generatedImage = mutableStateOf<String?>(null)
    val generatedImage: State<String?> = _generatedImage


    fun generateRandomImage() = viewModelScope.launch(Dispatchers.IO) {
        repository.generateRandomImage(this)
            .onEach { resource ->
                when(resource) {
                    is Resource.Error -> {
                        hideLoading()
                        showMessage(resource.message?:"")
                    }
                    is Resource.Loading -> {
                        showLoading(resource.message?:"Loading...")
                    }
                    is Resource.Success -> {
                        hideLoading()
                        resource.data?.let {
                            _generatedImage.value = it
                        }?: kotlin.run {
                            showMessage("Image Not Generated!")
                        }
                    }
                }
            }.launchIn(this)
    }


    private fun showLoading(message: Any) {
        _progressAndErrorState.value = progressAndErrorState.value.copy(
            loading = Pair(message,true)
        )
    }

    fun showMessage(message: Any,duration:Long = Constants.SNACKBAR_DURATION) {
        _progressAndErrorState.value = progressAndErrorState.value.copy(
            message = Pair(message,true),
            loading = Pair("",false)
        )
        viewModelScope.launch {
            delay(duration)
            hideSnackBar()
        }
    }

    private fun hideSnackBar() {
        _progressAndErrorState.value = progressAndErrorState.value.copy(
            message = Pair("",true)
        )
    }

    private fun hideLoading() {
        _progressAndErrorState.value = progressAndErrorState.value.copy(
            loading = Pair("",false)
        )
    }
}