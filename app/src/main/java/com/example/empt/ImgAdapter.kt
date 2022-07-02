package com.example.empt


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.galleryitem.view.*
import com.bumptech.glide.Glide
class ImgAdapter(private val context: Context) : RecyclerView.Adapter<ImgAdapter.ViewHolder>() {

    var ImgData = mutableListOf<Img>()
    override fun getItemCount(): Int = ImgData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ImgData[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.galleryitem, parent, false)
        return ImgAdapter.ViewHolder(view)
    }

    // 각 항목에 필요한 기능을 구현
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val imgProfile: ImageView = v.findViewById(R.id.MyImage)
        fun bind(item: Img) {
            Glide.with(itemView).load(item.image).into(imgProfile)
        }
    }
}
