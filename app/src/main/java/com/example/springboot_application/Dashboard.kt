package com.example.springboot_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

private lateinit var welcomeText:String
private lateinit var tvWelcome: TextView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        welcomeText = "Welcome " + intent.getStringExtra("username").toString() + "!"
        tvWelcome =this.findViewById(R.id.tvWelcome)
        tvWelcome.setText(welcomeText);
    }
}