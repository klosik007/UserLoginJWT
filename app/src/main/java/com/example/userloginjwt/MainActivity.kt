package com.example.userloginjwt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userloginjwt.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var JSONObj: JSONObject
    private val URL: String = "localhost/JWT_Auth/index.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            //login to service
        }

        binding.signupBtn.setOnClickListener{
            //sign up new user
        }
    }


}