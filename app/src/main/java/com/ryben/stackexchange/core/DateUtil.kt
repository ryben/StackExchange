import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val defaultFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

fun Long.toFormattedDate(
    zoneId: ZoneId = ZoneId.systemDefault(),
    formatter: DateTimeFormatter = defaultFormatter
): String {
    return Instant.ofEpochSecond(this)
        .atZone(zoneId)
        .format(formatter)
}