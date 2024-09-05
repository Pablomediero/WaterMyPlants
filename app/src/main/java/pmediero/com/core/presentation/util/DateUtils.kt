package pmediero.com.core.presentation.util
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

fun Calendar.setTimeToMillis(hour: Int, minute: Int, second: Int = 0, millisecond: Int = 0, day: Int = 0): Long {
    this.apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, second)
        set(Calendar.MILLISECOND, millisecond)
        add(Calendar.DAY_OF_YEAR, day)
    }
    return this.timeInMillis
}

fun Long.calculateTimeLog(tagLog: String, titleMessage: String){
    val hours = this / (1000 * 60 * 60)
    val minutes = (this % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = ((this % (1000 * 60 * 60)) % (1000 * 60)) / 1000
    Log.i(tagLog, "$titleMessage: Time remaining: $hours hours, $minutes minutes, $seconds seconds")

}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toStringFormat(): String {
    return this.format(DateTimeFormatter.ISO_LOCAL_DATE)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
}