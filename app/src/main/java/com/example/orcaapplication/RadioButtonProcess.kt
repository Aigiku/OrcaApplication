package com.example.orcaapplication

import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.PI

class RadioButtonProcess(
    private val radioGroup: RadioGroup,
    private val circleEdView: ConstraintLayout,
    private val squareEdView: ConstraintLayout,
    private val magnificationEdView: ConstraintLayout,
    private val explanationView: ConstraintLayout

) {
    init {
        setupRadioGroupListener()
    }

    fun setupRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_circle_title -> {
                    circleEdView.visibility = View.VISIBLE
                    squareEdView.visibility = View.GONE
                    magnificationEdView.visibility = View.GONE
                    explanationView.visibility = View.GONE
                }
                R.id.radio_square_title -> {
                    circleEdView.visibility = View.INVISIBLE
                    squareEdView.visibility = View.VISIBLE
                    magnificationEdView.visibility = View.GONE
                    explanationView.visibility = View.GONE

                }
                R.id.radio_magnification_title -> {
                    circleEdView.visibility = View.INVISIBLE
                    squareEdView.visibility = View.GONE
                    magnificationEdView.visibility = View.VISIBLE
                    explanationView.visibility = View.GONE
                }
            }
        }
    }
}

    class CalculateCircle(
         val circleMyDiameter: EditText,
         val circleMyHeight: EditText,
         val circleRecipeDiameter: EditText,
         val circleRecipeHeight: EditText
    ) {
        fun calculateCircle(): Double {

            //半径をそれぞれ求める
            val myRadius = (circleMyDiameter.text.toString().toDoubleOrNull() ?: 0.0) / 2
            val myHeight = circleMyHeight.text.toString().toDoubleOrNull() ?: 0.0

            val recipeRadius = (circleRecipeDiameter.text.toString().toDoubleOrNull() ?: 0.0) / 2
            val recipeHeight = circleRecipeHeight.text.toString().toDoubleOrNull() ?: 0.0

            val myVolume = PI * myRadius * myRadius * myHeight
            val recipeVolume = PI * recipeRadius * recipeRadius * recipeHeight

            val circleResult = myVolume / recipeVolume

            return circleResult
        }

        fun circleInputItemEmptyJudgment(): Boolean {
            return (
                    circleMyDiameter.text.toString().isEmpty() ||
                    circleMyHeight.text.toString().isEmpty() ||
                    circleRecipeDiameter.text.toString().isEmpty() ||
                    circleRecipeHeight.text.toString().isEmpty())
        }

        fun circleInputItemSizeJudgment(): Boolean {
            return (circleMyDiameter.text.toString().toDouble() > 30 ||
                    circleMyHeight.text.toString().toDouble() > 30 ||
                    circleRecipeDiameter.text.toString().toDouble() > 30 ||
                    circleRecipeHeight.text.toString().toDouble() > 30)
        }
    }


    class CalculateSquare(
        val squareMyVertical: EditText,
        val squareMyWidth: EditText,
        val squareMyHeight: EditText,
        val squareRecipeVertical: EditText,
        val squareRecipeWidth: EditText,
        val squareRecipeHeight: EditText
    ) {
        fun calculateSquare(): Double {
            val myHeight = squareMyHeight.text.toString().toDoubleOrNull() ?: 0.0
            val myWidth = squareMyWidth.text.toString().toDoubleOrNull() ?: 0.0
            val myVertical = squareMyVertical.text.toString().toDoubleOrNull() ?: 0.0

            val recipeHeight = squareRecipeHeight.text.toString().toDoubleOrNull() ?: 0.0
            val recipeWidth = squareRecipeWidth.text.toString().toDoubleOrNull() ?: 0.0
            val recipeVertical = squareRecipeVertical.text.toString().toDoubleOrNull() ?: 0.0

            val myVolume = myVertical * myWidth * myHeight
            val recipeVolume = recipeVertical * recipeWidth * recipeHeight

            val squareResult = myVolume / recipeVolume

            return squareResult
        }

        //        空欄判定
        fun squareInputItemEmptyJudgment(): Boolean {
            return (
                    squareMyVertical.text.toString().isEmpty() ||
                            squareMyWidth.text.toString().isEmpty() ||
                            squareMyHeight.text.toString().isEmpty() ||
                            squareRecipeVertical.text.toString().isEmpty() ||
                            squareRecipeWidth.text.toString().isEmpty() ||
                            squareRecipeHeight.text.toString().isEmpty())
        }

        fun squareInputItemSizeJudgment(): Boolean {
            return (
                            squareMyVertical.text.toString().toDouble() > 30 ||
                            squareMyWidth.text.toString().toDouble() > 30 ||
                            squareMyHeight.text.toString().toDouble() > 30 ||
                            squareRecipeVertical.text.toString().toDouble() > 30 ||
                            squareRecipeWidth.text.toString().toDouble() > 30 ||
                            squareRecipeHeight.text.toString().toDouble() > 30)
        }
    }


    class Magnification(val magnification: EditText){

        fun getMagnification(): Double {
            val magnificationText = magnification.text.toString()
            return magnificationText.toDoubleOrNull() ?: 0.0
        }
    }
