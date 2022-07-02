package com.example.empt


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlin.random.Random

class EditActivity : AppCompatActivity() {
    private lateinit var etvTitle: TextView
    private lateinit var etvContent: TextView
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var tableName: String

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        etvTitle = findViewById(R.id.editText_edit_title)
        etvContent = findViewById(R.id.editText_edit_content)
        btnSave = findViewById(R.id.btn_edit_save)
        btnCancel = findViewById(R.id.btn_edit_cancel)

        // 인텐트 받음
        val intent = intent
        val title = intent.extras?.getString("title")
        val content = intent.extras?.getString("content")
        val position = intent.extras?.getInt("position")

        etvTitle.text = title
        etvContent.text = content
        // 내용 작성

        btnSave.setOnClickListener {
            dbHelper = DatabaseHelper(this)
            database = dbHelper.writableDatabase
            tableName = "noteData"

            val sdfNow = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().time)
            val time = sdfNow.toString()
            val saveData = ContentValues()
            saveData.put("title", etvTitle.text.toString())
            saveData.put("content", etvContent.text.toString())
            saveData.put("time", time)

            database.update("noteData", saveData, "_id = ?", arrayOf(position.toString()))
            // database.update("noteData", contentValues, "_id=?", new String[] {String.valueOf(position)});
            startActivity(Intent(this, SubActivity3::class.java))
            finish()
        }

        btnCancel.setOnClickListener {
            startActivity(Intent(this, SubActivity3::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, SubActivity3::class.java))
        finish()
    }
}