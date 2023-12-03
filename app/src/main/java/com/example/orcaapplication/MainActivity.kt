package com.example.orcaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRadioGroupListener()
    }



    private fun editIdManeger(){

        val circleMyDiameter = findViewById<EditText>(R.id.circle_my_mold_diameter)
        val circleMyHeight = findViewById<EditText>(R.id.circle_my_mold_height)

        val circleRecipeDiameter = findViewById<EditText>(R.id.circle_recipe_mold_mold_diameter)
        val circleRecipeHeight = findViewById<EditText>(R.id.circle_recipe_mold_height)



        val squareMyVertical =findViewById<EditText>(R.id.square_my_mold_vertical)
        val squareMyWidth =findViewById<EditText>(R.id.square_my_mold_width)
        val squareMyHeight =findViewById<EditText>(R.id.square_my_mold_height)

        val squareRecipeVertical =findViewById<EditText>(R.id.square_recipe_mold_vertical)
        val squareRecipeWidth =findViewById<EditText>(R.id.square_recipe_mold_weight)
        val squareRecipeHeight =findViewById<EditText>(R.id.square_recipe_mold_height)


        val magnification = findViewById<EditText>(R.id.ed_magnification)


//
//        square_my_mold_vertical
//        square_my_mold_width
//        square_my_mold_height
//        square_recipe_mold_vertical
//        square_recipe_mold_weight
//        square_recipe_mold_height
//        circle_my_mold_diameter
//        circle_my_mold_height
//        circle_recipe_mold_height
//        circle_recipe_mold_mold_diameter


    }




    private fun setupRadioGroupListener() {
//        ラジオボタンの選択に応じて表示を制御する関数
        val radioGroup = findViewById<RadioGroup>(R.id.mold_tybe_radiogroup)
        val circleEdView = findViewById<ConstraintLayout>(R.id.circle_edview)
        val squareEdView = findViewById<ConstraintLayout>(R.id.square_edview)
        val magnificationEdView = findViewById<ConstraintLayout>(R.id.magnification_edview)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->

//         circleEdViewは適用ボタンの位置制約の為、非表示の場合はINVISIBLEにする
            when (checkedId) {
                R.id.radio_circle_title -> {
                    circleEdView.visibility = View.VISIBLE
                    squareEdView.visibility = View.INVISIBLE
                    magnificationEdView.visibility = View.INVISIBLE
                }
                R.id.radio_square_title -> {
                    circleEdView.visibility = View.INVISIBLE
                    squareEdView.visibility = View.VISIBLE
                    magnificationEdView.visibility = View.INVISIBLE
                }
                R.id.radio_magnification_title -> {
                    circleEdView.visibility = View.INVISIBLE
                    squareEdView.visibility = View.INVISIBLE
                    magnificationEdView.visibility = View.VISIBLE
                }
            }
        }
    }



}