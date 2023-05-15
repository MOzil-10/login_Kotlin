package com.example.springboot_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class splash : AppCompatActivity() {

   lateinit var ivloginIcon :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       ivloginIcon = findViewById(R.id.iv_loginIcon)

        ivloginIcon.alpha = 0f
        ivloginIcon.animate().setDuration(1500).alpha(1f).withEndAction {
            val intent = Intent(this@splash, Login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}