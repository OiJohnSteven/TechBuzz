package com.techbuzz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PersonAdapter(val context: Context, val list:ArrayList<Person>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.news_details, parent, false)

        return PersonHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PersonHolder).bind(list[position].description, list[position].field, list[position].image)

    }
    class PersonHolder(PersonView: View) : RecyclerView.ViewHolder(PersonView)
    {
        val field:TextView = PersonView.findViewById(R.id.author)
        val authdes:TextView = PersonView.findViewById(R.id.authdes)
        val authimage:ImageView = PersonView.findViewById(R.id.authimage)
        val context = PersonView.context
        fun bind(n:String, d:String, i:String)
        {
            field.text = n
            authdes.text =  d

            /*var remove = "https://conducted-deduction.000webhostapp.com/images/$i"
            remove = remove.replace(" ", "%20") */
            Glide.with(context).load("https://conducted-deduction.000webhostapp.com/images/$i").into(authimage)

        }
    }

}
