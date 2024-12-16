package com.example.newroomproject.ui.dashBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newroomproject.R
import com.example.newroomproject.databinding.FragmentDashBoardBinding

class DashBoardFragment : Fragment() {

    private val binding: FragmentDashBoardBinding by lazy { FragmentDashBoardBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }
}