package com.example.orcaapplication

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class IngredientAdapter(private val itemList: MutableList<ListItemData>, private var useValue: Double)
    : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {



    //    各行のビュー要素を保持するための内部クラスを定義。
    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.list_ingredience_name)
        val itemAmountBefore: TextView = itemView.findViewById(R.id.list_ingredience_before_amout)
        //計算後の値を表示　itemAmountAfter
        val itemAmountAfter: TextView = itemView.findViewById(R.id.list_ingredience_after_amout)
        init {
            itemView.setOnClickListener {
                val position:Int = adapterPosition
                //データクラスで定義されているname[position]だけを取得。
                val item = itemList[position]

                //１４）アラートダイアログ
                AlertDialog.Builder(itemView.context)
                    .setTitle(item.name)
                    .setMessage("上記の項目を削除しますか？")
                    .setPositiveButton("削除する"){ _, _ ->
                        itemList.removeAt(position)
                        //表示を更新
                        notifyItemRemoved(position)
                    }
                    .setNegativeButton("しない",null)
                    .show()
            }
        }
    }





    //各ラジオボタンの入力から計算結果を受け取り、リスト全体に知らせる。
    fun updateResult(useValue: Double) {
        this.useValue = useValue
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredients_row, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = itemList[position]


        holder.itemName.text = item.name
        holder.itemAmountBefore.text = String.format("%.1f",item.amount)

        val result: Double = item.amount * useValue
        val formattedResult = String.format("%.1f", result)
        holder.itemAmountAfter.text = formattedResult

    }
}