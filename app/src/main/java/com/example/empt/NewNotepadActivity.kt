package com.example.empt

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_new_notepad.*
import kotlin.random.Random

class NewNotepadActivity : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    private lateinit var etvTitle: EditText
    private lateinit var etvContent: EditText

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var tableName: String

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notepad)

        dbHelper = DatabaseHelper(this)
        database = dbHelper.writableDatabase
        tableName = "noteData"

        btnSave = findViewById(R.id.btn_save_new)
        btnCancel = findViewById(R.id.btn_cancel_new)

        etvTitle = findViewById(R.id.editText_title_newNotepad)
        etvContent = findViewById(R.id.editText_contents_newNotepad)

        btnSave.setOnClickListener {
            if (etvTitle.text.isEmpty() && etvContent.text.isEmpty()) {
                Toast.makeText(this, "작성한 내용이 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                val title = etvTitle.text.toString()
                val content = etvContent.text.toString()
                val sdfNow = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().time)
                val time = sdfNow.toString()

                val saveData = ContentValues()

                saveData.put("title", title)
                saveData.put("content", content)
                saveData.put("time", time)
                saveData.put("color", Random.nextInt(5).toString())

                database.insert(tableName, null, saveData)

                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, SubActivity3::class.java))
                finish()
            }
        }

        btnCancel.setOnClickListener {
            finish()
            startActivity(Intent(this, SubActivity3::class.java))
        }
    }

    override fun onBackPressed() {
        finish()
        startActivity(Intent(this, SubActivity3::class.java))
    }
}