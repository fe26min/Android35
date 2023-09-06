package fastcampus.part0.chapter2

import fastcampus.part0.chapter2.Book.Novel.NAME


fun main() {
    Counter
    println(Counter.count)

    Counter.countUp()
    Counter.countUp()

    println(Counter.count)

    Counter.hello()

    NAME

}

object Counter : Hello() {
    init {
        println("카운터 초기화")
    }
    var count = 0
    fun countUp() {
        count++
    }
}

open class Hello() {
    fun hello() = println("Hello")
}

class Book {
    // static과 같은 역할
    companion object Novel {
        const val NAME = "name"
        fun create() = Book()
    }
}