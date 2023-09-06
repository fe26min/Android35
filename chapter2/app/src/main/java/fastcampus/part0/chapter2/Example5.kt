package fastcampus.part0.chapter2

fun main() {
    val person = Person("수지", 24)
    val dog = Dog("해피", 24)

    println(person.toString())
    println(dog.toString())
    println(dog.copy(age = 3))

    val cat: Cat = BlueCat()
    val result = when(cat) {
        is BlueCat -> "blue"
        is RedCat -> "red"
        is GreenCat -> "green"
    }
    println(result)
}
class Person(
    val name : String,
    val age : Int,
) {
//    override fun toString(): String {
//        return "Person(name=$name, age=$age)"
//    }
}
data class Dog(
    val name: String,
    val age: Int,
)
// sealed class
// 컴파일러에게 자식이 누가 있는지를 알 수 있다. -> 컴파일러가 체크를 해줄 수 있다.
sealed class Cat
class BlueCat : Cat()
class RedCat : Cat()
class GreenCat : Cat()

