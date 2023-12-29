package svg.assignment.presentation.common.model

data class ScreenState(
    val loading: Pair<Any,Boolean> = Pair("",false),
    val message: Pair<Any,Boolean> = Pair("",false),
) {
    fun isShowing(): Boolean {
        return loading.second || message.second
    }
}