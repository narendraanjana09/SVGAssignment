package svg.assignment.presentation.recentlyGeneratedImages

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import svg.assignment.core.Utils.showToast
import svg.assignment.domain.repository.RecentlyGeneratedImagesRepository
import javax.inject.Inject

@HiltViewModel
class RecentlyGeneratedImagesViewModel @Inject constructor(
    private val repository: RecentlyGeneratedImagesRepository,
):ViewModel() {

    private val _generatedImages = mutableStateListOf<String>()
    val generatedImages: List<String> = _generatedImages

    fun getAllRecentlyGeneratedImages() = viewModelScope.launch {
        repository.getRecentlyGeneratedImages().collectLatest {
            _generatedImages.addAll(it)
        }
    }

    fun clearAllImages() = viewModelScope.launch {
        repository.clearAllImages().collectLatest {
            if(it) {
                _generatedImages.clear()
            }
        }
    }
}