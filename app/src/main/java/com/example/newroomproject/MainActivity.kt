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
import com.example.newroomproject.databinding.FragmentDashBoardBinding
import com.example.newroomproject.ui.dashBoard.DashBoardFragment
import com.example.newroomproject.ui.startScreen.StartScreenFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        lifecycleScope.launch{
            if (viewModel.getRowCount()){
                supportFragmentManager.commit{
                    replace<DashBoardFragment>(R.id.fragment_container_view)
                }
            } else{
                supportFragmentManager.commit{
                    replace<StartScreenFragment>(R.id.fragment_container_view)
                }
            }
        }




    }
}