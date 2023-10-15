package com.fe26min.part2.part2.chapter04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fe26min.part2.part2.chapter04.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}