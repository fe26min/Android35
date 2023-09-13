package fastcampus.part1.chapter6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import fastcampus.part1.chapter6.databinding.ActivityMainBinding
import fastcampus.part1.chapter6.databinding.DialogCountdownSettingBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener{
            showCountdownSettingDialog()
        }

        binding.startButton.setOnClickListener {
            start()
            setVisibleButton(false, false, true, true)
        }
        binding.stopButton.setOnClickListener {
            showAlertDialog()
        }
        binding.pauseButton.setOnClickListener {
            pause()
            setVisibleButton(true, true, false, false)
        }
        binding.lapButton.setOnClickListener {
            lap()
        }
    }

    private fun start() {

    }

    private fun stop() {
        Log.d("check", "1")
        setVisibleButton(true, true, false, false)
    }

    private fun pause() {

    }
    private fun lap() {

    }

    private fun setVisibleButton(start: Boolean, stop: Boolean, pause: Boolean, lap: Boolean) {
        binding.startButton.isVisible=start
        binding.stopButton.isVisible=stop
        binding.pauseButton.isVisible=pause
        binding.lapButton.isVisible=lap
    }

    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }
            setTitle("카운트다운 설정")
            setView(dialogBinding.root)
            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value
                binding.countdownTextView.text = String.format("%02d", countdownSecond)
            }
            setNegativeButton("취소", null)
        }.show()
    }


    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니오", null)
        }.show()
    }
}