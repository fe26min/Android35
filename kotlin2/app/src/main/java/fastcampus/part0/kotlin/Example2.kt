package fastcampus.part0.kotlin

import android.provider.ContactsContract.CommonDataKinds.Nickname

fun main() {
    println("Hello world!!")
    test01()
    var result = test02(1, 3)
    println(result)
    result = test02(1)
    println(result)
    test03(id="상아님", name = "채상아", nickname = "상아")
    println(time(1,3))
    println(times(1,3))
}

// 2. 함수
fun test01() : Unit {
    println("test")
}

fun test02(a: Int, b: Int) : Int {
    println(a+b)
    return a+b
}

fun test02(a: Int): Int {
    val b = 3
    println(a+b)
    return a+b
}

fun test03(name: String, nickname: String, id:String) = println(name + nickname + id)

fun times(a:Int, b:Int) : Int {
    return a * b
}

// 싱글 익스프레션
fun time(a:Int, b:Int) = a * b