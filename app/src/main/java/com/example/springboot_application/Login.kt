package com.example.springboot_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

lateinit var etUsername: EditText
lateinit var etPassword: EditText
lateinit var btnRegister: Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)

        var etButton=findViewById<Button>(R.id.btnLogin)
        etButton.setOnClickListener {
            //inside here we tell the function what to do
            //Toast.makeText(this, "Has been registered", Toast.LENGTH_SHORT).show();
            login(etUsername, etPassword)
        }
    }


    private fun login(Name: EditText, Pass: EditText) {
        val userName: String = Name.getText().toString().trim()
        val password: String = Pass.getText().toString().trim()

        val call: Call<ResponseBody> = RetrofitClient
            .getInstance()
            .api
            .checkUser(User(userName, password))

        if (userName.isEmpty()) {
            Name.setError("Username is required")
            Name.requestFocus()
            return
        } else if (password.isEmpty()) {
            Pass.setError("Password is required")
            Pass.requestFocus()
            return
        }

        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {

                }
                if (s == userName) {
                    val intent = Intent(this@Login,Dashboard::class.java)
                    intent.putExtra("Username",s)
                    Toast.makeText(this@Login, "Successfully login", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "User does not exists!", Toast.LENGTH_LONG).show()
                }


            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@Login, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }
}


