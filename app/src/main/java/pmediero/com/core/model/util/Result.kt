package pmediero.com.core.model.util


typealias RootError = Error

sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E?) : Result<D, E>

    fun fold(
        onError: (E?) -> Any,
        onSuccess: (D) -> Any
    ): Any =
        when (this) {
            is Error -> onError(error)
            is Success -> onSuccess(data)
        }
}

fun <D, E : RootError> Result<D, E>.asEmptyDataResult(): EmptyDataResult = Result.Success(Unit)

inline fun <D, E : RootError, M> Result<D, E>.map(map: (D) -> M): Result<M, E> {
    return when (this) {
        is Result.Error -> Result.Error(null)
        is Result.Success -> Result.Success(data = map(data))
    }
}

typealias EmptyDataResult = Result<Unit, Error>