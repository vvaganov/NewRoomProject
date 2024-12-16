package com.example.newroomproject.ui.startScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newroomproject.R
import com.example.newroomproject.databinding.FragmentDashBoardBinding
import com.example.newroomproject.databinding.FragmentStartScreenBinding

class StartScreenFragment : Fragment() {

    private val binding: FragmentStartScreenBinding by lazy { FragmentStartScreenBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_screen, container, false)
    }
}