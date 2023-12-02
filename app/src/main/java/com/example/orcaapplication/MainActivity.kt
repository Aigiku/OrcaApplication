package com.example.orcaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.mold_tybe_radiogroup)

        val circleEdView = findViewById<ConstraintLayout>(R.id.circle_edview)
        val squareEdView = findViewById<ConstraintLayout>(R.id.square_edview)
        val magnificationEdView = findViewById<ConstraintLayout>(R.id.magnification_edview)


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_circle_title -> {
                    circleEdView.visibility = View.VISIBLE
                    squareEdView.visibility = View.GONE
                    magnificationEdView.visibility = View.GONE
                }

                R.id.radio_square_title-> {
                    circleEdView.visibility = View.GONE
                    squareEdView.visibility = View.VISIBLE
                    magnificationEdView.visibility = View.GONE



                }
                R.id.radio_magnification_title -> {
                    circleEdView.visibility = View.GONE
                    squareEdView.visibility = View.GONE
                    magnificationEdView.visibility = View.VISIBLE

                }
            }
        }
    }
}