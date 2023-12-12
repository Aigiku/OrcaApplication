package com.example.orcaapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var calculateCircle: CalculateCircle
    private lateinit var calculateSquare: CalculateSquare
    private lateinit var returnMagnification: Magnification
    private lateinit var applyButton: Button
    private lateinit var addButton: Button
    private var circleResult: Double = 0.0
    private var squareResult: Double = 0.0
    private var magnificationResult: Double = 0.0
    val itemList = mutableListOf<ListItemData>()

    private lateinit var recyclerView: RecyclerView
    private var adapterUseValue: Double = 0.0
    private var adapter = IngredientAdapter(itemList, adapterUseValue)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ラジオボタンの項目とグループ
        val radioGroup = findViewById<RadioGroup>(R.id.mold_tybe_radiogroup)
        val circleEdView = findViewById<ConstraintLayout>(R.id.circle_edview)
        val squareEdView = findViewById<ConstraintLayout>(R.id.square_edview)
        val magnificationEdView = findViewById<ConstraintLayout>(R.id.magnification_edview)
        val exlanationView = findViewById<ConstraintLayout>(R.id.explanation_view_no_radiobutton)

        //アダプターで使用するリスト
        val listMultipleView = findViewById<TextView>(R.id.list_multiple_view)

        //アダプターやlistMultipleViewの表示に使う変数
        listMultipleView.text = adapterUseValue.toString()

//        小数点第二までで切り捨てる。
        val decimalFormat = DecimalFormat("#.##")

        val alertProsess = AlertProsess(this)
        applyButton = findViewById(R.id.applybtn)
        addButton = findViewById(R.id.addbtn)

//    アダプターの設定。
        adapter = IngredientAdapter(itemList, adapterUseValue)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {

            val itemName: EditText = findViewById(R.id.ed_ingredients_name)
            val itemAmountBefore: EditText = findViewById(R.id.ed_ingredients_amount)
            val itemNames = itemName.text.toString()
            val itemAmount = itemAmountBefore.text.toString().toDoubleOrNull() ?: 0.0

            if (itemNames.isEmpty() || itemAmountBefore.text.toString().isEmpty()) {
                alertProsess.showEmptyAlert()
                return@setOnClickListener
            } else if (itemNames.length >= 11) {
                alertProsess.showNameTooLongAlert()
                return@setOnClickListener
            } else if (itemAmount >= 2001){
                alertProsess.showNumLongAlert()
                return@setOnClickListener

            }


            val newItem = ListItemData(itemNames, itemAmount)
            itemList.add(newItem)
            adapter.notifyItemInserted(itemList.lastIndex)

            itemName.text.clear()
            itemAmountBefore.text.clear()

        }


        //以下、ラジオボタン関連の処理群

        //ラジオボタンのグループとその中の項目を渡す。
        val radioButtonProcess = RadioButtonProcess(
            radioGroup, circleEdView, squareEdView, magnificationEdView,exlanationView
        )

        //選択されているラジオボタンに応じてレイアウトの表示を切り替える
        radioButtonProcess.setupRadioGroupListener()


        // 各ラジオボタンレイアウトで使用されている項目(EditText)をクラスに渡す。
        calculateCircle = CalculateCircle(
            findViewById(R.id.circle_my_mold_diameter),
            findViewById(R.id.circle_my_mold_height),
            findViewById(R.id.circle_recipe_mold_mold_diameter),
            findViewById(R.id.circle_recipe_mold_height)
        )

        calculateSquare = CalculateSquare(
            findViewById(R.id.square_my_mold_vertical),
            findViewById(R.id.square_my_mold_width),
            findViewById(R.id.square_my_mold_height),
            findViewById(R.id.square_recipe_mold_vertical),
            findViewById(R.id.square_recipe_mold_weight),
            findViewById(R.id.square_recipe_mold_height)
        )

        //倍率指定のみそのまま値を返すだけ
        returnMagnification = Magnification(
            findViewById(R.id.ed_magnification)
        )


        // 選択されているラジオボタンに基づいて結果を計算し、表示する
//        各戻り値をadapterUseValueに格納し、applyボタンを押した時にその値をアダプターに渡すようにする。
        applyButton.setOnClickListener {

            when (radioGroup.checkedRadioButtonId) {
                R.id.radio_circle_title -> {
                    //入力欄の空欄チェック。
                    if (circleEdreturn()) {
                        alertProsess.showEmptyAlert()
                        return@setOnClickListener
                    }
//                    空欄でなければ、倍率を渡す
                    circleResult = calculateCircle.calculateCircle()
                    adapterUseValue = decimalFormat.format(circleResult).toDouble()
                    adapter.updateResult(adapterUseValue)
                    listMultipleView.text = adapterUseValue.toString()

                }

                R.id.radio_square_title -> {

                    if (squareEdreturn()) {
                        alertProsess.showEmptyAlert()
                        return@setOnClickListener
                    }
                    squareResult = calculateSquare.calculateSquare()
                    adapterUseValue = decimalFormat.format(squareResult).toDouble()
                    adapter.updateResult(adapterUseValue)
                    listMultipleView.text = adapterUseValue.toString()
                }

                R.id.radio_magnification_title -> {

                    if (returnMagnification.magnification.text.toString().isEmpty()) {
                        alertProsess.showEmptyAlert()
                        return@setOnClickListener
                    } else if (returnMagnification.magnification.text.toString().toDouble() > 10 ){
                        alertProsess.showMagnifiNumLongAlert()
                        return@setOnClickListener
                    }

                    magnificationResult = returnMagnification.getMagnification()
                    adapterUseValue = decimalFormat.format(magnificationResult).toDouble()
                    adapter.updateResult(adapterUseValue)
                    listMultipleView.text = adapterUseValue.toString()
                }
            }
        }
    }





    //各レイアウトのIDを取得する関数。　空欄判定のifに使用
    fun circleEdreturn(): Boolean {
        return(
                calculateCircle.circleMyDiameter.text.toString().isEmpty() ||
                calculateCircle.circleMyHeight.text.toString().isEmpty() ||
                calculateCircle.circleRecipeDiameter.text.toString().isEmpty()||
                calculateCircle.circleRecipeHeight.text.toString().isEmpty()
                )
    }

    fun squareEdreturn(): Boolean {
        return (calculateSquare.squareMyVertical.text.toString().isEmpty() ||
                calculateSquare.squareMyHeight.text.toString().isEmpty() ||
                calculateSquare.squareMyWidth.text.toString().isEmpty() ||
                calculateSquare.squareRecipeVertical.text.toString().isEmpty() ||
                calculateSquare.squareRecipeHeight.text.toString().isEmpty() ||
                calculateSquare.squareRecipeWidth.text.toString().isEmpty()
                )
    }

}


