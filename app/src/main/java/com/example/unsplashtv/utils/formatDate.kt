import java.text.SimpleDateFormat
import java.util.*

fun formatDate(input: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    parser.timeZone = TimeZone.getTimeZone("UTC")
    val date = parser.parse(input) ?: return "Invalid Date"

    val formatter = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
    return formatter.format(date)
}