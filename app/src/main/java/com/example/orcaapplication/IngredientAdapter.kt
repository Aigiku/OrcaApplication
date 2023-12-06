package com.example.orcaapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class IngredientAdapter(private val itemList: MutableList<ListItemData>, private var useValue: Double)
    : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {




    //    各行のビュー要素を保持するための内部クラスを定義。
    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.list_ingredience_name)
        val itemAmountBefore: TextView = itemView.findViewById(R.id.list_ingredience_before_amout)
        //計算後の値を表示　itemAmountAfter
        val itemAmountAfter: TextView = itemView.findViewById(R.id.list_ingredience_after_amout)
    }

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
        holder.itemAmountBefore.text = String.format("%.2f",item.amount)

        val result: Double = item.amount * useValue
        val formattedResult = String.format("%.2f", result)
        holder.itemAmountAfter.text = formattedResult

    }
}






//メモよう

//        holder.amountCulTextView.text = (item.amount * 3).toString()



//        holder.itemAmountAfter.text = String.format("%.2f", result)

//
//
//class MyAdapter(private val itemList: List<ListItem>) :
//    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
//        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
//        val amountCulTextView: TextView = itemView.findViewById(R.id.amountCulTextView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_item_layout, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val item = itemList[position]
//        holder.nameTextView.text = item.name
//        holder.amountTextView.text = item.amount.toString()
//        holder.amountCulTextView.text = (item.amount * 3).toString()
//    }
//
//    override fun getItemCount(): Int {
//        return itemList.size
//    }
//}
//
//
//
////
////
////package com.example.recycleviewtest2
////
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.Button
////import android.widget.TextView
////import androidx.recyclerview.widget.RecyclerView
//
//
//class RecyclerAdpter(private val myList: MutableList<TodoData>):RecyclerView.Adapter<RecyclerAdpter.ViewHolderItem>() {
//
//    //inner classを作る
//
//    //class内にリストを用意
//
//
//    inner class ViewHolderItem(v: View) : RecyclerView.ViewHolder(v) {
//        val holderName: TextView = v.findViewById(R.id.editTextText)
//        val holderNumber: TextView = v.findViewById(R.id.editTextNumber)
//        val adbtn: Button = v.findViewById(R.id.addbutton)
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
//        //1行だけのビューを作成
//        val itemXml = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row, parent, false)
//        return ViewHolderItem(itemXml)
//    }
//
//
//    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
//        //n番目のデータをセットし表示
//        val currentItem = myList[position]
//        holder.holderName.setText(currentItem.myTodo)
//        holder.holderNumber.setText(currentItem.myDate.toString())
//
//        holder.adbtn.setOnClickListener {
//            val name = holder.holderName.text.toString()
//            val weight = holder.holderNumber.text.toString().toDoubleOrNull()
//
//
//            if (name.isNotEmpty() && weight != null) {
//                // データをリストに追加
//                myList.add(TodoData(name, weight))
//                notifyItemInserted(myList.size - 1)
//
//                // 入力欄をクリア
//                holder.holderName.text = ""
//                holder.holderNumber.text = ""
//
//            }
//        }
//    }
//
//
//    override fun getItemCount(): Int {
//        //        リストサイズ
//        return myList.size
//    }
//}