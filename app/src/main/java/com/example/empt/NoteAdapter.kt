package com.example.empt

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val items: List<NoteList>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>()  {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recyclerview, parent, false)

        return ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: NoteList) {
            val title: TextView = view.findViewById(R.id.textView_title_recyclerView)
            val contents: TextView = view.findViewById(R.id.textView_contents_recyclerView)

            title.text = item.title
            contents.text = item.contents

//            when(item.color) {
//                "0" -> view.setBackgroundResource(R.drawable.rounding_one)
//                "1" -> view.setBackgroundResource(R.drawable.rounding_one)
//                "2" -> view.setBackgroundResource(R.drawable.rounding_one)
//                "3" -> view.setBackgroundResource(R.drawable.rounding_one)
//                "4" -> view.setBackgroundResource(R.drawable.rounding_one)
//                else -> view.setBackgroundResource(R.drawable.rounding_one)
//            }

            view.setOnClickListener { v ->

                val dbHelper = DatabaseHelper(view.context)
                val database = dbHelper.writableDatabase
                val context = view.context
                val sort = (v.context as SubActivity3).sort

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val cursor: Cursor
                    if (sort == "desc") {
                        cursor = database.rawQuery(
                            "select _id, title, content, time, color from noteData order by time DESC",
                            null
                        )
                    } else {
                        cursor = database.rawQuery(
                            "select _id, title, content, time, color from noteData order by time ASC",
                            null
                        )
                    }
                    cursor.move(position + 1)
                    val intent = Intent(view.context, EditActivity::class.java)
                    intent.putExtra("title", cursor.getString(1))
                    intent.putExtra("content", cursor.getString(2))
                    intent.putExtra("time", cursor.getString(3))
                    intent.putExtra("position", cursor.getInt(0))
                    context.startActivity(intent)
                    // ((Activity)context).finish(); ????????? ??????
                    (context as Activity).finish()
                    cursor.close()
                }
            }

            // ?????? ?????? -> ??? ??????
            view.setOnLongClickListener { v ->
                val database: SQLiteDatabase
                val context = v.context
                val dbHelper = DatabaseHelper(v.context)
                database = dbHelper.writableDatabase
                val sort = (v.context as SubActivity3).sort

                val alertDialog = AlertDialog.Builder(context)
                var cursor: Cursor
                alertDialog.setMessage("?????????????????????????")
                alertDialog.setPositiveButton("???") { _, _ ->
                    val position = adapterPosition
                    cursor = if (sort == "desc") {
                        database.rawQuery(
                            "select _id, title, content from noteData order by time DESC",
                            null
                        )
                    } else {
                        database.rawQuery(
                            "select _id, title, content from noteData order by time ASC",
                            null
                        )
                    }
                    cursor.move(position + 1)
                    Toast.makeText(v.context, "?????????????????????.", Toast.LENGTH_SHORT).show()
                    database.delete("noteData", "_id=?",
                        arrayOf(cursor.getInt(0).toString())
                    )
                    cursor.close()
                    if (sort == "desc")
                        (v.context as SubActivity3).refreshListDesc()
                    else
                        (v.context as SubActivity3).refreshListAsc()
                }

                alertDialog.setNegativeButton("??????") { _, _ -> }//?????? ??????
                alertDialog.create().show()
                false
            }
        }
    }
}