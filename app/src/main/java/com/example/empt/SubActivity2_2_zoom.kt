package com.example.empt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class SubActivity2_2_zoom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_layout)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sub_2_2, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backtoscroll -> {
                val intent_two_two_zoom_grid = Intent(this,SubActivity2_2::class.java)
                startActivity(intent_two_two_zoom_grid)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}