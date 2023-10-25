import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // ui에 오늘, 어제 택스트 더해줌
    fun formatCustomDate(inputDate: String): String {
        val (date, today, yesterday) = dateTriple(inputDate)
        val formatter = DateTimeFormatter.ofPattern("M월 d일")

        return when (date) {
            today -> "오늘 ${date.format(formatter)}"
            yesterday -> "어제 ${date.format(formatter)}"
            else -> date.format(formatter)
        }
    }

    // 생일인 사람 상단을오 오게 정렬, 어제 인 사람을 그다음으로 정렬
    fun getDateOrder(inputDate: String): Int {
        val (date, today, yesterday) = dateTriple(inputDate)

        return when (date) {
            today -> 0
            yesterday -> 1
            else -> 2
        }
    }

    private fun dateTriple(inputDate: String): Triple<LocalDate, LocalDate, LocalDate> {
        val date = LocalDate.parse(inputDate, dateFormatter)
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)
        return Triple(date, today, yesterday)
    }
}
