package com.example.userloginjwt

import android.content.Context
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject

class LoginAttempt(ctx: Context, email: String, password: String): NetworkOperations(){
    private val url: String = "localhost/JWT_Auth/api/login.php"
    private val email = email
    private val password = password
    private val ctx = ctx

    init{
        require(this.email.isNotEmpty()) { "Email cannot be empty" }
        require(this.password.isNotEmpty()) {"Password cannot be empty"}
    }

    override fun doWorkBeforeBackground() {
        //nothing to do
    }

    override fun doInBackground(): JSONObject? {
        val params = mutableMapOf<String, String>()
        params["email"] = email
        params["password"] = password

        return JSONParser.makeHttpRequest(url, "POST", params)
    }

    override fun doWorkAfterBackground(json: JSONObject?) {
        try {
            if (json != null) {
                Toast.makeText(ctx, json.getString("message"), Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(ctx, "Unable to retrieve any data from serwvr", Toast.LENGTH_LONG).show()
            }
        }catch (e:JSONException){
            e.printStackTrace()
        }
    }
}