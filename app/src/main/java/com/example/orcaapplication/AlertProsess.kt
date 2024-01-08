package com.example.orcaapplication

import android.app.AlertDialog
import android.content.Context

class AlertProsess(private val context: Context) {


    fun showEmptyAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("エラー：入力欄が空欄")
            .setMessage("すべての項目を入力してください")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showNameTooLongAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("エラー：文字数")
            .setMessage("材料名は10文字以下にしてください")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showNumLongAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("エラー：重量")
            .setMessage("重量は2000g以下にしてください")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showMagnifiNumLongAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("エラー：倍率")
            .setMessage("倍率は10倍以下にしてください")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showTypeSizeAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("エラー：数値")
            .setMessage("各項目は30cm以下で入力してください")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}