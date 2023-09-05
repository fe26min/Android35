package fastcampus.part0.kotlin

fun main() {
    max(10, 3)
    isHoliday("금")
}

// 자바에서는 스테이트 였던 것이 코틀린에서는 익스프레션으로 할 수 있다.
fun max(a: Int, b: Int) {
    val result = if(a>b) a else b
    println(result)
}

// 월 화 수 목 금 토 일
fun isHoliday(dayOfWeek: Any) {
    when(val day = dayOfWeek) {
        "토",
        "일" -> if(day == "토") "좋아" else "너무 좋아"
        in 2..4 -> {}
        in listOf("월", "화") -> {}
        else -> "안좋아"
    }
}