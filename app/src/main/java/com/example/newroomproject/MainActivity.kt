package com.example.newroomproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newroomproject.databinding.ActivityMainBinding
import com.example.newroomproject.databinding.FragmentDashBoardBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }
}