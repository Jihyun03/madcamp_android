package com.example.empt

import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_button1.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject



class SubActivity1 : AppCompatActivity() {
    lateinit var PNAdapter : ListAdapter
    var phonenumlist = arrayListOf<PhoneNum>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button1)
//        val status = ContextCompat.checkSelfPermission(this,"android.permission.READ_CONTACTS")
//        if(status==PackageManager.PERMISSION_GRANTED){
//            Log.d("test","permission granted")
//            startProcess()
//        }
//        else{
//            ActivityCompat.requestPermissions(this,arrayOf<String>("android.permission.READ_CONTACTS"),100)
//            Log.d("test","permission denied")
//        }

        //way1. json 파일 그대로 읽어오기
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

        //way2. 내장 주소록에서 받아오기
//        val proj = arrayOf(
//            ContactsContract.Contacts._ID,
//            ContactsContract.Contacts.DISPLAY_NAME,
//            ContactsContract.CommonDataKinds.Phone.NUMBER
//        )
//        val cursor = contentResolver.query(
//            Uri.parse("content://com.android.contacts/data/phones"),
//            proj, null, null, null);
//        while (cursor.moveToNext()) {
//            var index = cursor.getColumnIndex(proj[0])
//            val id = cursor.getStirng(index)
//            index = cursor.getColumIndex(proj[1])
//            val person_name = cursor.getStirng(index)
//            val person_phonenum = cursor
//            val music = PhoneNum(person_name, person_phonenum)
//            musicList.add(music)}
//
        PNAdapter = PNListAdapter(this,phonenumlist)
        phonenum_list.adapter = PNAdapter
        backbutton1.setOnClickListener {
            finish()
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            Log.d("test","permission granted")
//            startProcess()
//        }
//        else{
//            Log.d("test","permission denied")
//        }
//    }


}