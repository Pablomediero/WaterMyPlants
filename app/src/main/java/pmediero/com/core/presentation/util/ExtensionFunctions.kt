package pmediero.com.core.presentation.util

fun String.truncate(maxLength: Int): String {
    return if (length > maxLength) {
        substring(0, maxLength - 3) + "..."
    } else {
        this
    }
}