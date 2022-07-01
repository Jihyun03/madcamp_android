package com.example.empt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_button2.*
import kotlinx.android.synthetic.main.activity_sub22.*

class SubActivity2_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub22)

        backbutton2_2.setOnClickListener {
            this@SubActivity2_2.finishAffinity()
        }
        scrollview_button.setOnClickListener ({
            val intent2_scroll = Intent(this,SubActivity2::class.java)
            startActivity(intent2_scroll)
        })
    }
}