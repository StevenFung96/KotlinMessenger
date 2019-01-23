package com.example.hexa_stevenfung.kotlinmessenger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            performLogin()
        }

        back_to_register_textview.setOnClickListener {
            finish()
        }
    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("Main", "Successfully login user with uid: ${it.result?.user?.uid}")
                Toast.makeText(
                    this,
                    "Successfully login user with uid: ${it.result?.user?.uid}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Log.d("Main", "Falied to login user account: ${it.message}")
                Toast.makeText(this, "Falied to login user account: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}