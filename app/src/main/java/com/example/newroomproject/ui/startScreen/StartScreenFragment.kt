package com.example.newroomproject.ui.startScreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.newroomproject.R
import com.example.newroomproject.utils.Converters
import com.example.newroomproject.utils.DatePickerFragment
import com.example.newroomproject.data.user.UserParamsEntity
import com.example.newroomproject.databinding.FragmentStartScreenBinding
import com.example.newroomproject.ui.dashBoard.DashBoardFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.getValue
import kotlin.toString

@AndroidEntryPoint
class StartScreenFragment : Fragment() {

    private val binding: FragmentStartScreenBinding by lazy {
        FragmentStartScreenBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: StartScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formater = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val data = LocalDateTime.now()

        binding.idLayoutDateOfBirth.setEndIconOnClickListener {
            DatePickerFragment { dataTime ->
                binding.idInputDateOfBirth.setText(data.format(formater))
            }
        }

        binding.buttonSaveParams.setOnClickListener {
            viewModel.insertUserParams(
                UserParamsEntity(
                    id = 0,
                    data = data.format(formater),
                    name = binding.idInputName.text.toString(),
                    weight = binding.idInputWeight.text.toString().toDouble(),
                    height = binding.idInputHeight.text.toString().toInt(),
                    dateOfBirth = binding.idInputDateOfBirth.text.toString(),
                    gender = binding.idInputGender.text.toString()
                )
            )

            parentFragmentManager.commit{
                replace<DashBoardFragment>(R.id.fragment_container_view, "DashBoardFragment")
            }
        }
    }

}