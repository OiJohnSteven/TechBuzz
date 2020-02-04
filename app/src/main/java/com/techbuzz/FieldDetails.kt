package com.techbuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_field_details.*

class FieldDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_details)

        val field:String = intent.getStringExtra("field")!!

        val url = "https://katalambano.000webhostapp.com/getdetails.php?field=$field"

        val list = ArrayList<News>()

        val request = Volley.newRequestQueue(this)
        val json = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener{ response ->

            for (x in 0..response.length()-1)
                list.add(News(response.getJSONObject(x).getInt("id"),response.getJSONObject(x).getString("author"), response.getJSONObject(x).getString("description"), response.getJSONObject(x).getString("image")))

            val adp = NewsAdapter(this, list)
            recycler.layoutManager = LinearLayoutManager(this)

            recycler.adapter = adp
            Toast.makeText(this, "Great", Toast.LENGTH_LONG).show()


        }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

        })

        request.add(json)


    }
}
