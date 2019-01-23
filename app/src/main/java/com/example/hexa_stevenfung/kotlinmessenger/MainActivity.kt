package com.example.hexa_stevenfung.kotlinmessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {
            performRegister()
        }

        already_have_account_text_view.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {
        val username = username_edittext_register.text.toString()
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        //Firebase Authentication to register
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
                Toast.makeText(this, "Successfully created user with uid: ${it.result?.user?.uid}", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener {
                Log.d("Main", "Falied to create user: ${it.message}")
                Toast.makeText(this, "Falied to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
