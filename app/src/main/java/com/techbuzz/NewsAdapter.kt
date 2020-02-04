package com.techbuzz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_details.view.*

class NewsAdapter(val context: Context, val list:ArrayList<News>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.news_details, parent, false)

        return NewsHolder(view)
            }

    override fun getItemCount(): Int {
       return list.size
           }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsHolder).bind(list[position].author, list[position].description, list[position].image)

            }
    class NewsHolder(NewsView: View) : RecyclerView.ViewHolder(NewsView)
    {
        val author:TextView = NewsView.findViewById(R.id.author)
        val authdes:TextView = NewsView.findViewById(R.id.authdes)
        val authimage:ImageView = NewsView.findViewById(R.id.authimage)
        val context = NewsView.context
        fun bind(n:String, d:String, i:String)
        {
            author.text = n
           authdes.text =  d

            /*var remove = "https://conducted-deduction.000webhostapp.com/images/$i"
            remove = remove.replace(" ", "%20") */
            Glide.with(context).load("https://conducted-deduction.000webhostapp.com/images/$i").into(authimage)

        }
    }

}
