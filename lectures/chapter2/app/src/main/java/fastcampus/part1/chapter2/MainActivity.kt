package fastcampus.part1.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
//        Log.d("ActivityLog", "Create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberTextView = findViewById<TextView>(R.id.numberTextViewId)
        val resetButton = findViewById<Button>(R.id.resetButtonId)
        val plusButton = findViewById<Button>(R.id.plusButtonId)

        numberTextView.text = number.toString()

        resetButton.setOnClickListener {
//            Log.d("onClick", "리셋 버튼이 클릭 됐습니다.")
            number = 0
            numberTextView.text = number.toString()
        }

        plusButton.setOnClickListener {
//            Log.d("onClick", "더하기 버튼이 클릭 됐습니다.")
            number++
            numberTextView.text = number.toString()
        }
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d("ActivityLog", "Start")
//    }
//    override fun onResume() {
//        super.onResume()
//        Log.d("ActivityLog", "Resume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("ActivityLog", "Resume")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("ActivityLog", "Stop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("ActivityLog", "Destroy")
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("number", number)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        number = savedInstanceState.getInt("number")

        val numberTextView = findViewById<TextView>(R.id.numberTextViewId)
        numberTextView.text = number.toString()

        super.onRestoreInstanceState(savedInstanceState)
    }
}