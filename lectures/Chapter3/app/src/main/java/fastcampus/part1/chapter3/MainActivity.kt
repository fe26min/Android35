package fastcampus.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 화면 요소를 가져올 변수
    private lateinit var binding: ActivityMainBinding
    private var cmToM = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create되면서 bind에 화면을 연결
        // inflate 를 통해 xml 에 있는 뷰를 객체화.
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 화면에 최상위 root 를 가지고 온다.
        val view = binding.root
        // 결과값 :  view type is class androidx.constraintlayout.widget.ConstraintLayout
        Log.d("typeCheck", "view type is ${view.javaClass}")

        setContentView(view)

        // 바인딩과 기존 findViewById 차이
        // 바인딩은 화면에 id가 다른 화면과 공유되지 않는다.
        // findViewById에서는 만약 id가 중복할 경우 NullPointerException이 발생한다.

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton
        var inputNumber : Int = 0


        // 문자가 변경되면 알려줘
        inputEditText.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            if (cmToM) {
                outputTextView.text = String.format("%.2f", inputNumber.times(0.01))
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        swapImageButton.setOnClickListener {
            cmToM = !cmToM
            if(cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"

                outputTextView.text = String.format("%.2f", inputNumber.times(0.01))
            }
            else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"

        super.onRestoreInstanceState(savedInstanceState)
    }
}