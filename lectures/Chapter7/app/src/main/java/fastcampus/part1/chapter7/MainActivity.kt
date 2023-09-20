package fastcampus.part1.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , WordAdapter.ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private var selectedWord:Word? = null
    private val updateAddWordResult = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()

    ) {result ->
        Log.d("test00", "{$result ")
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false
        Log.d("test01", "{$isUpdated}")
        if(result.resultCode == RESULT_OK && isUpdated) {
            Log.d("test01", "${result.resultCode}")
            Toast.makeText(this,"check", Toast.LENGTH_SHORT)
            updateAddWord()
        }
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.addButton.setOnClickListener{

            Intent(this, AddActivity::class.java).let {
                updateAddWordResult.launch(it)
            }
        }

        binding.deleteImageView.setOnClickListener {
            delete()
        }
    }

    override fun onResume() {
        super.onResume()
    }


    private fun initRecyclerView() {
        wordAdapter = WordAdapter(mutableListOf(), this)

        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
        Thread {
            val list = AppDataBase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            wordAdapter.list.addAll(list)
            runOnUiThread{ wordAdapter.notifyDataSetChanged() }
        }.start()
    }

    private fun updateAddWord() {
        Log.d("test1", "1")
        Thread {
            AppDataBase.getInstance(this)?.wordDao()?.getLatestWord()?.let {word ->
                wordAdapter.list.add(0, word)
                runOnUiThread { wordAdapter.notifyDataSetChanged() }
            }
        }.start()
    }

    private fun delete() {
        if(selectedWord == null) return

        Thread {
            selectedWord?. let {word ->
                AppDataBase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    binding.textTextView.text = ""
                    binding.meanTextView.text = ""
                    Toast.makeText(this, "삭제가 완료됐습니다.", Toast.LENGTH_SHORT)
                }
                selectedWord = null
            }
        }.start()
    }
    override fun onClick(word: Word) {
        selectedWord = word
        binding.textTextView.text = word.text
        binding.meanTextView.text = word.mean

        Toast.makeText(this, "${word.text} 가 클릭 됐습니다", Toast.LENGTH_SHORT).show()
    }
}