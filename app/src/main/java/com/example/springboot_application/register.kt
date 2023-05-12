package com.example.springboot_application

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class register : AppCompatActivity() {

    //These lines declare three lateinit properties(etUsername, etPassword, btnRegister) of types EditText and Button.
    // They will be initialized later in the onCreate method.

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        //username and password must be initialized with the one in the xml file


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //binding the register activity class and the activity_register.xml
        //findViewById is the one that binds the layout

        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)
        btnRegister = findViewById(R.id.btnRegister)

        //this function will make the button clickable
        btnRegister.setOnClickListener {

            //inside here we tell the function what to do
            //Toast.makeText(this, "Has been registered", Toast.LENGTH_SHORT).show();
            registerUser()
        }

        this.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, login::class.java))
        }

    }

    private fun registerUser() {
        val userName: String = etUsername.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()

        // Validation checks for empty username and password
        if (userName.isEmpty()) {
            etUsername.setError("Username is required")
            etUsername.requestFocus()
            return
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required")
            etPassword.requestFocus()
            return
        }


        // Create a Retrofit API call to register the user
        val call: Call<ResponseBody> = RetrofitClient.getInstance().api.createUser(User(userName, password))

        call.enqueue(object : Callback<ResponseBody?> {

            // Callback for response handling
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s == "SUCCESS") {
                    Toast.makeText(
                        this@register,
                        "Successfully registered. Please login",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@register, login::class.java))
                } else {
                    Toast.makeText(this@register, "User already exists!", Toast.LENGTH_LONG)
                        .show()
                }

//
            }


            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {

                Toast.makeText(this@register, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }
}