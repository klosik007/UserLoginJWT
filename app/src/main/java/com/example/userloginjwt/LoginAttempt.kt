package com.example.userloginjwt

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit

class LoginAttempt(email: String, password: String): NetworkOperations(){
    private val email = email
    private val password = password

    private lateinit var retrofit: Retrofit
    private lateinit var service: APIService
    private lateinit var reqBody: RequestBody
    private lateinit var response: Response<ResponseBody>

    private val url: String = "https://10.0.2.2/JWT_Auth/api/login.php/"

    init{
        require(this.email.isNotEmpty()) { "Email cannot be empty" }
        require(this.password.isNotEmpty()) {"Password cannot be empty"}
    }

    override fun doWorkBeforeBackground() {
        retrofit = Retrofit.Builder().baseUrl(url).build()
        service = retrofit.create(APIService::class.java)

        val jsonObj = JSONObject()
        jsonObj.put("email", email)
        jsonObj.put("password", password)

        val jsonObjString = jsonObj.toString();
        reqBody = jsonObjString.toRequestBody("application/json".toMediaTypeOrNull())
    }

    override suspend fun doInBackground(): Response<ResponseBody> {
        response = service.login(reqBody)
        return response
    }

    override suspend fun doWorkAfterBackground(result: Response<ResponseBody>) {
        if(response.isSuccessful){
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(
                JsonParser.parseString(response.body()?.string())
            )
            Log.d("json pretty: ", prettyJson)
        }else{
            Log.e("RETROFIT_ERROR", response.code().toString())
        }
    }
}