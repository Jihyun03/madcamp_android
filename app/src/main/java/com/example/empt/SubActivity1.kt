package com.example.empt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_button1.*
import java.io.BufferedReader


class SubActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var phonenumlist = arrayListOf<PhoneNum>(
            PhoneNum("이지현","01090299439"),
            PhoneNum("하현수","01012345678")
        )
        //try{
            //BufferedReader br = new BufferedReader(new FileReader("phone_num_list.csv"))

        //}
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button1)
        val PNAdapter = PNListAdapter(this,phonenumlist)
        phonenum_list.adapter = PNAdapter
        backbutton1.setOnClickListener {
            finish()
        }
    }
}