package com.example.userloginjwt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userloginjwt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            val email = binding.emailTxt.text.toString()
            val password = binding.passwordTxt.text.toString()

            val login = LoginAttempt(email, password)
            login.perform()
        }

        binding.signupBtn.setOnClickListener{
            //sign up new user
        }
    }
}