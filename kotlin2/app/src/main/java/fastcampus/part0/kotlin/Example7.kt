package fastcampus.part0.kotlin

fun main() {
    // 코틀린에서의 리스트는 변경 가능 리스트, 변경 불가능 리스트가 있다
    // 이게 가장 큰 차이점

    val list = mutableListOf(1,2,3,4,5)
    list.add(6)
    list.addAll(listOf(7,8,9))

    val list1 = listOf(1,2,3,4)
    println(list1.joinToString ("/"))
    // 함수가 없다.
//    list1.add
    list1[0]

    println(list1.map { it * 10 }.joinToString ("/" ))

    val diverseList = listOf(1, "안녕", 1.78, true)

    println(list.joinToString(","))


    val map = mapOf((1 to "안녕"), (2 to "hello"))
    val map1 = mutableMapOf((1 to "안녕"), (2 to "hello"))
    map1.put(3,"응")
    map1[3] = "응"
    map1[100] = "호이"
}