package svg.assignment.core

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import svg.assignment.domain.model.RecentlyGeneratedImages

class RecentlyGeneratedImagesLruCache(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addImageToCache(image: String) {
        val cacheList = getCacheList().list.toMutableList()
        if (!cacheList.contains(image)) {
            if (cacheList.size >= MAX_CACHE_SIZE) {
                cacheList.removeLast()
            }
            cacheList.add(0,image)
            saveCacheList(RecentlyGeneratedImages(cacheList))
        }
    }

    fun getCacheList(): RecentlyGeneratedImages {
        val cacheJson = sharedPreferences.getString(KEY_CACHE_LIST, null)
        return if (cacheJson != null) {
            gson.fromJson(cacheJson, RecentlyGeneratedImages::class.java)
        } else {
            RecentlyGeneratedImages(emptyList())
        }
    }

    fun clearCache() {
        sharedPreferences.edit().remove(KEY_CACHE_LIST).apply()
    }

    private fun saveCacheList(recentlyGeneratedImages: RecentlyGeneratedImages) {
        val cacheJson = gson.toJson(recentlyGeneratedImages)
        sharedPreferences.edit().putString(KEY_CACHE_LIST, cacheJson).apply()
    }

    companion object {
        private const val MAX_CACHE_SIZE = 20
        private const val KEY_CACHE_LIST = "dogs_list"
    }
}
