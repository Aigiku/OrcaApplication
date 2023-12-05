package com.example.orcaapplication

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
    private lateinit var addButton:Button
    private var circleResult: Double = 0.0
    private var squareResult: Double = 0.0
    private var magnificationResult: Double = 0.0
    private val itemList = mutableListOf<ListItemData>()

//    private val itemList = mutableListOf<ListItemData>()
    private lateinit var recyclerView : RecyclerView
    private var adapterUseValue:Double = 0.0
    private var adapter = IngredientAdapter(itemList, adapterUseValue)

//    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val radioGroup = findViewById<RadioGroup>(R.id.mold_tybe_radiogroup)
        val circleEdView = findViewById<ConstraintLayout>(R.id.circle_edview)
        val squareEdView = findViewById<ConstraintLayout>(R.id.square_edview)
        val magnificationEdView = findViewById<ConstraintLayout>(R.id.magnification_edview)

        val listMultipleView = findViewById<TextView>(R.id.list_multiple_view)

        //アダプターやlistMultipleViewの表示に使う変数

        listMultipleView.text = adapterUseValue.toString()

//        小数点第二までで切り捨てる。
        val decimalFormat = DecimalFormat("#.##")


        applyButton = findViewById(R.id.applybtn)
        addButton = findViewById(R.id.addbtn)

//    アダプターの設定。
    adapter = IngredientAdapter(itemList,adapterUseValue)
    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)

    addButton.setOnClickListener {
//
        val itemName: EditText = findViewById(R.id.ed_ingredients_name)
        val itemAmountBefore: EditText = findViewById(R.id.ed_ingredients_amount)
        val itemNames = itemName.text.toString()
        val itemAmount = itemAmountBefore.text.toString().toDoubleOrNull() ?: 0.0

        val newItem = ListItemData(itemNames, itemAmount)
        itemList.add(newItem)
        adapter.notifyItemInserted(itemList.lastIndex)

        itemName.text.clear()
        itemAmountBefore.text.clear()

    }




    val radioButtonProcess = RadioButtonProcess(
            radioGroup, circleEdView, squareEdView, magnificationEdView
        )

    //選択されているラジオボタンに応じてレイアウトの表示を切り替える
        radioButtonProcess.setupRadioGroupListener()


    // 各ラジオボタンレイアウトで使用されている項目をクラスに渡す。
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
//        各戻り値をadapterUseValueに格納し、aplayボタンを押した時にその値をアダプターに渡すようにする。
        applyButton.setOnClickListener {

            when (radioGroup.checkedRadioButtonId) {
                R.id.radio_circle_title -> {
                    circleResult = calculateCircle.calculateCircle()
                    adapterUseValue = decimalFormat.format(circleResult).toDouble()
                    adapter.updateResult(adapterUseValue)
                    listMultipleView.text = adapterUseValue.toString()

                }

                R.id.radio_square_title -> {
                    squareResult = calculateSquare.calculateSquare()
                    adapterUseValue = decimalFormat.format(squareResult).toDouble()
                    adapter.updateResult(adapterUseValue)
                    listMultipleView.text = adapterUseValue.toString()
                }

                R.id.radio_magnification_title -> {
                    magnificationResult = returnMagnification.getMagnification()
                    adapterUseValue = decimalFormat.format(magnificationResult).toDouble()
                    adapter.updateResult(adapterUseValue)
                    listMultipleView.text = adapterUseValue.toString()
                }
            }
        }
    }
}


//メモ用　無視してOK
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        editTextName = findViewById(R.id.editTextName)
//        editTextAmount = findViewById(R.id.editTextAmount)
//        recyclerView = findViewById(R.id.recyclerView)
//
//        adapter = MyAdapter(itemList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter
//
//        val addButton: Button = findViewById(R.id.addButton)
//        addButton.setOnClickListener {
//            val name = editTextName.text.toString()
//            val amount = editTextAmount.text.toString().toDoubleOrNull()
//
//            if (!name.isNullOrEmpty() && amount != null) {
//                val listItem = ListItem(name, amount)
//                itemList.add(listItem)
//                adapter.notifyItemInserted(itemList.size - 1)
//                editTextName.text.clear()
//                editTextAmount.text.clear()
//            } else {
//                // Handle invalid input
//                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//    }
//}
