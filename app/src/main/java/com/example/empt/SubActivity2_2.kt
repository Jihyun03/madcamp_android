package com.example.empt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_button2.*
import kotlinx.android.synthetic.main.activity_sub22.*

class SubActivity2_2 : AppCompatActivity() {
    lateinit var imgadapter : ImgAdapter
    val ImgData = mutableListOf<Img>()
    private lateinit var gridManager : GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub22)
        gridManager = GridLayoutManager(this, 2)
        initRecycler()

        backbutton2_2.setOnClickListener {
            this@SubActivity2_2.finishAffinity()
        }
        scrollview_button.setOnClickListener ({
            val intent2_scroll = Intent(this,SubActivity2::class.java)
            startActivity(intent2_scroll)
        })
    }
    private fun initRecycler() {

         imgadapter = ImgAdapter(this)
         Recycle.adapter= imgadapter
         ImgData.apply {
             add(Img(image = R.drawable.cityscape1, id = 1))
             add(Img(image = R.drawable.cityscape2, id = 1))
             add(Img(image = R.drawable.woods1, id = 1))
             add(Img(image = R.drawable.woods2, id = 1))
             add(Img(image = R.drawable.photo1, id = 1))
             add(Img(image = R.drawable.space1, id = 1))
             add(Img(image = R.drawable.space2, id = 1))
             add(Img(image = R.drawable.soccer, id = 1))
             add(Img(image = R.drawable.sea1, id = 1))
             add(Img(image = R.drawable.police, id = 1))
             add(Img(image = R.drawable.plant1, id = 1))
             add(Img(image = R.drawable.nyang, id = 1))
             add(Img(image = R.drawable.maplestory, id = 1))
             add(Img(image = R.drawable.homework, id = 1))
             add(Img(image = R.drawable.flowers, id = 1))
             add(Img(image = R.drawable.fish, id = 1))
             add(Img(image = R.drawable.dog, id = 1))
             add(Img(image = R.drawable.basketball, id = 1))
             add(Img(image = R.drawable.study, id = 1))
             add(Img(image = R.drawable.time, id = 1))
             imgadapter.ImgData=ImgData
             imgadapter.notifyDataSetChanged()

         }

    }
}