package com.techbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val timedelay:Long= 3000
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, timedelay)
    }
}