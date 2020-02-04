package com.techbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : AppCompatActivity() {

    val TAG = "Create Account"

    private var mDatabase = FirebaseDatabase.getInstance()
    private var mDatabaseReference = mDatabase.reference.child("Users")
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        creaccount.setOnClickListener {
            createaccount()
        }
    }

    private fun createaccount() {


       val emailTxt = findViewById<EditText>(R.id.email)
        val email = emailTxt.text.toString()
        val passwordTxt = findViewById<EditText>(R.id.password)
        val password = passwordTxt.text.toString()
        val nameTxt = findViewById<EditText>(R.id.username)
        val name = nameTxt.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
        ) {

            mAuth!!
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val userId = mAuth!!.currentUser!!.uid
                        //Verify Email
                        verifyEmail()
                        //update user profile information
                        val currentUserDb = mDatabaseReference.child(userId)
                        currentUserDb.child("email").setValue(email)
                        currentUserDb.child("password").setValue(password)
                        currentUserDb.child("name").setValue(name)

                        updateUserInfoAndUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }

    }


    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Verification email sent to " + mUser.getEmail(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(
                        this,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
