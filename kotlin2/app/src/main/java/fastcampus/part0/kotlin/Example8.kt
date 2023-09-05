package fastcampus.part0.kotlin

fun main() {
    var name : String = "상아"
    var number: Int = 10

    var nickname: String? = null
    var secondNumber: Int? = null

//    val result = if(nickname == null) {
//        "값이 없음"
//    } else {
//        nickname
//    }

    // 오퍼레이터
    // 엘비스 오퍼레이터
    val result = nickname?: "값이 없음"

    val size = nickname?.length ?: 0

    println(result)
    println(size)
}