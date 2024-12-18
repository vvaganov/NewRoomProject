package com.example.newroomproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.newroomproject.databinding.ActivityMainBinding
import com.example.newroomproject.ui.dashBoard.DashBoardFragment
import com.example.newroomproject.ui.startScreen.StartScreenFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch {
            if (viewModel.getRowCount()) {
                if (supportFragmentManager.findFragmentByTag(DashBoardFragment::class.java.simpleName) == null) {
                    supportFragmentManager.commit {
                        replace<DashBoardFragment>(
                            R.id.fragment_container_view,
                            tag = DashBoardFragment::class.java.simpleName
                        )
                    }
                }

            } else {
                if (supportFragmentManager.findFragmentByTag(StartScreenFragment::class.java.simpleName) == null) {
                    supportFragmentManager.commit {
                        replace<StartScreenFragment>(
                            R.id.fragment_container_view,
                            tag = StartScreenFragment::class.java.simpleName
                        )
                    }
                }
            }
        }
    }
}