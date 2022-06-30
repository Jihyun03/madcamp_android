package com.example.empt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_button1.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject



class SubActivity1 : AppCompatActivity() {
    lateinit var PNAdapter : ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        var phonenumlist = arrayListOf<PhoneNum>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button1)
        try{
            val jsonString = assets.open("phone_num_list.json")
            val size = jsonString.available()
            val buffer = ByteArray(size)
            jsonString.read(buffer)
            jsonString.close()
            var strJson = String(buffer,Charsets.UTF_8)
            val jsonObject = JSONObject(strJson)
            val pnarray = jsonObject.getJSONArray("pnlist")
            var i = 0
            while(i<pnarray.length()){
                val jsonObject = pnarray.getJSONObject(i)
                val person = jsonObject.getString("name")
                val phone_num = jsonObject.getString("phonenum")
                val Phone_Num = PhoneNum(person,phone_num)
                phonenumlist.add(Phone_Num)
                i++
            }
        } catch (e:JSONException){
            e.printStackTrace()
        }
        PNAdapter = PNListAdapter(this,phonenumlist)
        phonenum_list.adapter = PNAdapter
        backbutton1.setOnClickListener {
            finish()
        }
    }
}