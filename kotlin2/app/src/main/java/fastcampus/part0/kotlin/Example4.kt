package fastcampus.part0.kotlin

fun main() {
    val user = User(name = "채상아")
    println(user.age)
    Kid("아이", 3, "male")

}

//코틀린은 상속에 대해서 닫혀 있다.
// 왜 상속에 대해서 닫아 놨을까?
open class User(open val name : String, open var age: Int = 100)

class Kid(override val name: String, override var age:Int) : User(name, age) {
    var gender : String = "male"

    init {
        println("초기화 중 입니다.")
    }

    constructor(name:String, age:Int, gender:String) : this(name, age) {
        this.gender = gender
        println("부생성자 호출")
    }
}
