package com.example.orcaapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class IngredienceAdapter(private val itemList :List<ListItemData>):
    RecyclerView.Adapter<IngredienceAdapter.ingredienceViewHolder>() {

    //    各行のビュー要素を保持するための内部クラスを定義。
    class ingredienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.ingredience_name)
        val itemAmountBefore: TextView = itemView.findViewById(R.id.ingredience_before_amout_amout)

        //計算後の値を表示　itemAmountAfter
        val itemAmountAfter: TextView = itemView.findViewById(R.id.ingredience_after_amout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ingredienceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredients_row, parent, false)
        return ingredienceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ingredienceViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemName.text = item.name
        holder.itemAmountBefore.text = item.amount.toString()
//        N倍した数値を表示させる
        holder.itemAmountAfter.text = (item.amount * 5).toString()
    }
}


