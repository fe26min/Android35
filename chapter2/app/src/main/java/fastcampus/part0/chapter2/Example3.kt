package fastcampus.part0.chapter2

// 스코프 함수가 나온 이유
// 객체의 컨텍스트 내에서, 실행 가능한 코드 블럭을 만드는 함수
// 차이점
    // 수신 객체 접근 방법

fun main() {
    // let, run, apply, also, with
    // 1. let
    // 수신객체.let { user ->
    //
    // 마지막 줄 // return
    // }
   val a = 3
   a.let { }
   var user : User? = User("채상아", 10, true)

    val userAge = user?.let {
        it.age
    }
    println(userAge)

    // 2. run
    // 수신객체.run { this ->
    //
    // 마지막 줄 return
    // }
    val kid = User("아이", 4, false)
    val kidAge = kid.run {
//        this.age
        age
    }
    println(kidAge)

    // 3. apply
    // 수신객체.apply { this ->
    //
    // }
    // return 값이 수신 객체
    val female = User("슬기", 20, true, true)
    val femaleValue = female.apply {
        hasGlasses = false
    }
    println(femaleValue.hasGlasses)

    // 4. also
    // 수신객체.also { it -> // local variable
    //
    //
    // } // return 수신객체 (자기자신)

    val male = User("민수", 17, false, true)
    val maleValue = male.also {
        println(it.name)
    }
    // 초기화 작업에 이름을 적어야 할까요?
    // also는 수신객체에 대한 로그를 찍을때 사용을 권장한다.
    //
    println(maleValue.hasGlasses)

    // 5. with
    // with(수신객체) {
    // ----
    // 마지막 줄
    // }
    // 위의 4가지는 수신객체를 확장하였음
    // with는 다름

    val result =with(male) {
        hasGlasses = false
        true
    }
    println(result)
}


class User(
    val name : String,
    val age : Int,
    val gender : Boolean, // true : female
    var hasGlasses : Boolean = true,
)