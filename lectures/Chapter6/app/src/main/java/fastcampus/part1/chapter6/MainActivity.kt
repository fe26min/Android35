package fastcampus.part1.chapter6

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import fastcampus.part1.chapter6.databinding.ActivityMainBinding
import fastcampus.part1.chapter6.databinding.DialogCountdownSettingBinding
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 10
    private var currentCountdownDeciSecond = countdownSecond * 10
    private var currentDeciSecond = 0
    private var timer: Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener {
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
        initCountdownViews()
    }

    private fun initCountdownViews() {
        val seconds = currentCountdownDeciSecond / 10
        binding.countdownTextView.text = String.format("%02d", seconds)
        binding.countdownProgressBar.progress = 100
    }

    private fun start() {
        timer = timer(initialDelay = 0, period = 100) {
            if (currentCountdownDeciSecond == 0) {

                currentDeciSecond += 1

                val minutes = currentDeciSecond.div(10) / 60
                val second = currentDeciSecond.div(10) % 60
                val deciSeconds = currentDeciSecond % 10

                runOnUiThread {
                    binding.timeTextView.text =
                        String.format("%02d:%02d", minutes, second)
                    binding.tickTextView.text = deciSeconds.toString()

                    binding.countdownGroup.isVisible = false

                }
            } else {
                currentCountdownDeciSecond -= 1

                val seconds = currentCountdownDeciSecond / 10
                val progress = (currentCountdownDeciSecond / (countdownSecond * 10f)) * 100

                binding.root.post {
                    binding.countdownTextView.text = String.format("%02d", seconds)
                    binding.countdownProgressBar.progress = progress.toInt()
                }
            }
            if (currentDeciSecond == 0 && currentCountdownDeciSecond < 31 && currentCountdownDeciSecond % 10 == 0) {
                val toneType = if(currentCountdownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone(toneType, 300)
            }
        }

    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun stop() {
        setVisibleButton(true, true, false, false)

        currentDeciSecond = 0
        binding.timeTextView.text = "00:00"
        binding.tickTextView.text = "0"

        binding.countdownGroup.isVisible = true
        initCountdownViews()
        binding.lapContainerLinearLayout.removeAllViews()
    }

    private fun lap() {
        if (currentDeciSecond == 0) return
        val container = binding.lapContainerLinearLayout
        val lapTextView = TextView(this).apply {
            textSize = 20f
            gravity = Gravity.CENTER
            val minutes = currentDeciSecond.div(10) / 60
            val seconds = currentDeciSecond.div(10) % 60
            val deciSeconds = currentDeciSecond % 10

            text = "${container.childCount.inc()}. " + String.format(
                "%02d:%02d %01d",
                minutes,
                seconds,
                deciSeconds
            )
            // 1. 01:03 0
            setPadding(30)
        }.let { lapTextView ->
            container.addView(lapTextView, 0)
        }
    }

    private fun setVisibleButton(start: Boolean, stop: Boolean, pause: Boolean, lap: Boolean) {
        binding.startButton.isVisible = start
        binding.stopButton.isVisible = stop
        binding.pauseButton.isVisible = pause
        binding.lapButton.isVisible = lap
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
                currentCountdownDeciSecond = countdownSecond * 10
                binding.countdownTextView.text = String.format("%02d", countdownSecond)
                binding.countdownProgressBar.progress = 100
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