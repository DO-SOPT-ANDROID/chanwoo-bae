package org.sopt.dosopttemplate.utils

sealed interface UiState<out T> {

    object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<Nothing>

    object Error : UiState<Nothing>
}
