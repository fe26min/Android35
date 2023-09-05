package fastcampus.part0.kotlin

fun main() {
//    1..10 == IntRange(1, 10)
    for(i in 1..10) {
        print(i)
        print(".")
    }
    println()
    for(i in IntRange(1, 10)) {
        print(i)
        print(".")
    }
    println()
    for(i in 1 until 10) {
        print(i)
        print(".")
    }
    println()
    for(i in 1..10 step(2)) {
        print(i)
        print(".")
    }
    println()
    for(i in 10 downTo 1) {
        print(i)
        print(".")
    }
    println()
    for(i in 10 downTo 1 step(2)) {
        print(i)
        print(".")
    }
    println()

    var c = 1
    while(c < 11) {
        print(c)
        print(".")
        c++
    }
}

