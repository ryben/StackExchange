import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateUtilsTest {

    private val utcZone = ZoneId.of("UTC")

    @Test
    fun `toFormattedDate returns correct string for UTC epoch`() {
        val timestamp = 1704067200L // Jan 1, 2024 00:00:00 UTC
        val result = timestamp.toFormattedDate(zoneId = utcZone)

        assertEquals("Jan 1, 2024", result)
    }

    @Test
    fun `toFormattedDate applies custom formatter`() {
        val timestamp = 1704067200L
        val customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val result = timestamp.toFormattedDate(
            zoneId = utcZone,
            formatter = customFormatter
        )

        assertEquals("2024-01-01", result)
    }

    @Test
    fun `toFormattedDate handles different timezones`() {
        val timestamp = 1704067200L // Jan 1, 2024 00:00:00 UTC
        val tokyoZone = ZoneId.of("Asia/Tokyo") // UTC+9

        val result = timestamp.toFormattedDate(zoneId = tokyoZone)

        // In Tokyo, this is Jan 1, 2024 09:00:00
        assertEquals("Jan 1, 2024", result)
    }
}