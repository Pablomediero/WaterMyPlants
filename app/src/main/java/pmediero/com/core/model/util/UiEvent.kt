package pmediero.com.core.model.util

sealed class UiEvent {
    data object Loading: UiEvent()
    data class Succes(val data: String): UiEvent()
    data class Error(val message: UiText) : UiEvent()
}