package fastcampus.part0.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SAM
        // Single Abstract Method
        val view = View(this)

        // SAM 함수형 인터페이스를 인자로 받는 자바 함수를 람다로 식을 대신 넘길 수 있다.
        view.setOnClickListener { println("안녕") }
    }
}