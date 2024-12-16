package com.example.newroomproject.model

import com.example.newroomproject.ui.dashBoard.ParamsMap
import kotlin.math.pow

interface BasicMetabolism {

    fun getBasicMetabolism(params: ParamsMap): Metabolism


    class HarrisBenedictMetabolism(): BasicMetabolism{

        override fun getBasicMetabolism(params: ParamsMap): Metabolism {
            var genderRatio = 0
            genderRatio = if (params.weight == 0.0) {
                0
            } else {
                if (params.gender == "Мужчина") 5 else -161
            }
            with(params) {
                val normalWeight = (height.toDouble() / 100).pow(2) * 21.75
                val excessWeightCoefficient = (weight - normalWeight) * 0.1

               return Metabolism(
                   (((9.99 * (normalWeight + excessWeightCoefficient)
                        + (6.25 * height) - (5 * age.toInt()) + genderRatio) * 1.1)))

            }
        }
    }
}

data class Metabolism(
    val basicMetabolism: Double
)