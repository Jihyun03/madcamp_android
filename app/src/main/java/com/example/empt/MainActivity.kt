package com.example.empt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_phonenum_book.setOnClickListener({
            val intent = Intent(this,SubActivity1::class.java)
            startActivity(intent)
        })
        btn_photo_book.setOnClickListener({
            val intent2 = Intent(this,SubActivity2::class.java)
            startActivity(intent2)
        })
        btn_memopad.setOnClickListener({
            val intent3 = Intent(this,SubActivity3::class.java)
            startActivity(intent3)
        })
    }
}