package com.techbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fields.*

class FieldsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fields)

        val url = "https://katalambano.000webhostapp.com/getuploads.php"
        val list= ArrayList<String>()
        val request:RequestQueue = Volley.newRequestQueue(this)
        val json = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length()-1)
                list.add(response.getJSONObject(x).getString("field"))

            val adp = ArrayAdapter(this, R.layout.fieldlist, list)
            listview.adapter = adp
            Toast.makeText(this, "Great", Toast.LENGTH_LONG).show()

        }, Response.ErrorListener { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

        })

        request.add(json)

        listview.setOnItemClickListener { adapterView, view, i, l ->

            val field:String = list[i]
            val obj = Intent(this, FieldDetails::class.java)
            obj.putExtra("field", field)
            startActivity(obj)
        }
    }
}
