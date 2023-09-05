package fastcampus.part0.kotlin

fun main() {
    val a = 10
    val name = "안녕"
    val isHigh = true

//    println(a.toString() + name + isHigh.toString())
//    String.format("%s %d", name, a)
    // 코틀린의 문자열 템플릿
    println("$a $name $isHigh")
    println("$a ${name.length} $isHigh")
}