package com.example.empt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_button2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub22.*
import kotlinx.android.synthetic.main.galleryitem.*

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
    }
    private fun initRecycler() {

         imgadapter = ImgAdapter(this)
         Recycle.adapter= imgadapter
         ImgData.apply {
             add(Img(image = R.drawable.cityscape1, id = 1))
             add(Img(image = R.drawable.cityscape2, id = 2))
             add(Img(image = R.drawable.woods1, id = 3))
             add(Img(image = R.drawable.woods2, id = 4))
             add(Img(image = R.drawable.photo1, id = 5))
             add(Img(image = R.drawable.space1, id = 6))
             add(Img(image = R.drawable.space2, id = 7))
             add(Img(image = R.drawable.soccer, id = 8))
             add(Img(image = R.drawable.sea1, id = 9))
             add(Img(image = R.drawable.police, id = 10))
             add(Img(image = R.drawable.plant1, id = 11))
             add(Img(image = R.drawable.nyang, id = 12))
             add(Img(image = R.drawable.maplestory, id = 13))
             add(Img(image = R.drawable.homework, id = 14))
             add(Img(image = R.drawable.flowers, id = 15))
             add(Img(image = R.drawable.fish, id = 16))
             add(Img(image = R.drawable.dog, id = 17))
             add(Img(image = R.drawable.basketball,id = 18))
             add(Img(image = R.drawable.study, id = 19))
             add(Img(image = R.drawable.time, id = 20))
             imgadapter.ImgData=ImgData
             imgadapter.notifyDataSetChanged()

         }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sub_2_2, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Toscroll -> {
                val intent_two_two_grid = Intent(this,SubActivity2::class.java)
                startActivity(intent_two_two_grid)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}