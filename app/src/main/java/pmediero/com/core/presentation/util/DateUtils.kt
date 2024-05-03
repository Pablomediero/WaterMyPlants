package pmediero.com.core.presentation.util
import java.util.Calendar

fun Calendar.setTimeToMillis(hour: Int, minute: Int, second: Int = 0, millisecond: Int = 0): Long {
    this.apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, second)
        set(Calendar.MILLISECOND, millisecond)
    }
    return this.timeInMillis
}

