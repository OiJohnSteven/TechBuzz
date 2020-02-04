package com.techbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    val mAuth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {

            val inputemail = findViewById<EditText>(R.id.email)
            val inputpassword = findViewById<EditText>(R.id.password)
            val email = inputemail.text.toString()
            val password = inputpassword.text.toString()

            if (!email.isEmpty() && !password.isEmpty()) {
                this.mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                             startActivity(Intent(this, MainActivity::class.java))
                             Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG)
                                 .show()

                        } else {
                            Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })

            }
            else{
                Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        create.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }
    }


}
