package com.techbuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_field_details.*
import kotlinx.android.synthetic.main.activity_uploads.*

class UploadsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploads)

        search.setOnClickListener {

            val name = findViewById<EditText>(R.id.txtfield)
            val author = name.text.toString()

            val url = "https://conducted-deduction.000webhostapp.com/loadauthor.php?author=$author"

            val list = ArrayList<Person>()

            val request = Volley.newRequestQueue(this)
            val json =
                JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->

                    for (x in 0..response.length() - 1)
                        list.add(
                            Person(
                                response.getJSONObject(x).getInt("id"),
                                response.getJSONObject(x).getString("description"),
                                response.getJSONObject(x).getString("field"),
                                response.getJSONObject(x).getString("image")
                            )
                        )

                    val adp = PersonAdapter(this, list)
                    uploads.layoutManager = LinearLayoutManager(this)

                    uploads.adapter = adp
                    Toast.makeText(this, "Great", Toast.LENGTH_LONG).show()


                }, Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

                })

            request.add(json)
        }
    }
}