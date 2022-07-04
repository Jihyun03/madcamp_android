package com.example.empt

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ListAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_button1.*


class SubActivity1 : AppCompatActivity() {
    lateinit var PNAdapter : ListAdapter
    var phonenumlist = arrayListOf<PhoneNum>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button1)
        val status = ContextCompat.checkSelfPermission(this,"android.permission.READ_CONTACTS")
        if(status== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,arrayOf<String>("android.permission.READ_CONTACTS"),99)
        }
        else{
            startProcess()
        }

        //way1. json 파일 그대로 읽어오기
//        try{
//            val jsonString = assets.open("phone_num_list.json")
//            val size = jsonString.available()
//            val buffer = ByteArray(size)
//            jsonString.read(buffer)
//            jsonString.close()
//            var strJson = String(buffer,Charsets.UTF_8)
//            val jsonObject = JSONObject(strJson)
//            val pnarray = jsonObject.getJSONArray("pnlist")
//            var i = 0
//            while(i<pnarray.length()){
//                val jsonObject = pnarray.getJSONObject(i)
//                val person = jsonObject.getString("name")
//                val phone_num = jsonObject.getString("phonenum")
//                val Phone_Num = PhoneNum(person,phone_num)
//                phonenumlist.add(Phone_Num)
//                i++
//            }
//        } catch (e:JSONException){
//            e.printStackTrace()
//        }
        PNAdapter = PNListAdapter(this,phonenumlist)
        phonenum_list.adapter = PNAdapter
        backbutton1.setOnClickListener {
            finish()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startProcess()
        }
        else{
            Toast.makeText(this@SubActivity1,"설정에서 연락처 접근 권한을 허용해야합니다.",Toast.LENGTH_SHORT).show()
        }
    }
    //way2. 내장 주소록에서 받아오기
    fun startProcess() {
        val addressUri = ContactsContract.Contacts.CONTENT_URI
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val proj = arrayOf(ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER)
        val cursor = contentResolver.query(phoneUri,proj,null,null,null)
        while(cursor?.moveToNext()?:false){
            val id = cursor?.getString(0)
            val name = cursor?.getString(1)
            val number = cursor?.getString(2)
            val phone = PhoneNum(name,number)
            phonenumlist.add(phone)
        }
    }




}