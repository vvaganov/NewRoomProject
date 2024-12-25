package com.example.newroomproject.ui.calorieSpendList

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newroomproject.databinding.ItemConsumptionBinding
import com.example.newroomproject.R
import com.example.newroomproject.data.user.CalorieSpendEntity


class CalorieSpendListAdapter(var dataSet: List<CalorieSpendEntity>) :
    RecyclerView.Adapter<CalorieSpendListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemConsumptionBinding.bind(view)
        val data: TextView = binding.cardDataConsump
        val value: TextView = binding.cardValueConsump
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consumption, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Log.i("!!!", "viewModelDataSet - $dataSet")
        val state = dataSet[position]
        holder.data.text = state.data
        holder.value.text = state.value.toString()
    }

    override fun getItemCount(): Int = dataSet.size
}