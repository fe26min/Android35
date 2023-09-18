package fastcampus.part1.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import fastcampus.part1.chapter7.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        binding.addButton.setOnClickListener{
            add()
        }

    }

    private fun initViews() {
        val types = listOf(
            "명사", "동사", "대명사", "형용사", "부사", "감탐사", "전치사", "접속사"
        )
        binding.typeChipGroup.apply {
            types.forEach { text ->
                addView(createChip(text))
            }
        }
    }

    private fun createChip(text: String) : Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add () {
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEdit.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val word = Word(text, mean, type)

        AppDataBase.getInstance(this)?.wordDao()?.insert(word)
        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}