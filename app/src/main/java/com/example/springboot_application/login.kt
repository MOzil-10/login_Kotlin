package com.example.springboot_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private lateinit var txtLogin: TextView
class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

         var etUsername: EditText
         var etPassword: EditText
         var bntLogin: Button


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)
        bntLogin = findViewById(R.id.btnLogin)

txtLogin = findViewById(R.id.tvRegisterLink)


        txtLogin.setOnClickListener {
            intent = Intent(this, register::class.java)
            startActivity(intent)
        }
    }
}