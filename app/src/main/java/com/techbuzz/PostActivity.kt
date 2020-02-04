package com.techbuzz

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_post.*
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        /*val url = "https://conducted-deduction.000webhostapp.com/upload.php"
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
         */

        post.setOnClickListener{
           checkPermission()

        }

    }

    val READIMAGE = 234
    fun checkPermission(){

        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf( android.Manifest.permission.READ_EXTERNAL_STORAGE),READIMAGE)
                return
            }
        }

        loadImage()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            READIMAGE->{
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    loadImage()
                }else{
                    Toast.makeText(applicationContext,"Cannot access your images", Toast.LENGTH_LONG).show()
                }
            }
            else-> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }


    }

    val PICK_IMAGE_CODE=123
    fun loadImage(){

        val intent= Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==PICK_IMAGE_CODE  && data!=null && resultCode == RESULT_OK){

            val selectedImage = data.data
            try {

                val bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage)
                attach.setImageBitmap(bitmap)
                uploadImage(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun uploadImage( bitmap: Bitmap){
        val jsonObject =  JSONObject()
        val rQueue = Volley.newRequestQueue(this)
        val URL = "https://katalambano.000webhostapp.com/userupload.php"

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val encodedImage:String = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        try {

            val imgname = (Calendar.getInstance().timeInMillis).toString()
            jsonObject.put("name", imgname)
            //  Log.e("Image name", etxtUpload.getText().toString().trim());
            jsonObject.put("image", encodedImage)
            // jsonObject.put("aa", "aa")
        } catch (e: JSONException) {
            Log.e("JSONObject Here", e.toString())
        }
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, URL, jsonObject,
            Response.Listener<JSONObject> {
               response ->
                    Log.e("aaaaaaa", jsonObject.toString())
                    rQueue.getCache().clear()
                    Toast.makeText(getApplication(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show()

            }, Response.ErrorListener {error ->

                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

            })

        rQueue.add(jsonObjectRequest)

    }


}