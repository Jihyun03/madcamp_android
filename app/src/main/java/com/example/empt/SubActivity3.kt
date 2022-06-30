package com.example.empt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_button3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject



class SubActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button3)

        backbutton3.setOnClickListener {
            finish()
        }
    }
}