package com.example.userloginjwt

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

abstract class NetworkOperations {
    abstract fun doWorkBeforeBackground()
    abstract fun doInBackground(): JSONObject?
    abstract fun doWorkAfterBackground(json: JSONObject?)

    fun perform(){
        doWorkBeforeBackground()//preExecute
        CoroutineScope(Dispatchers.IO).launch {//doInBackground
            try {
                val api = doInBackground()
                withContext(Dispatchers.Main) {
                    try {
                        doWorkAfterBackground(api)//onPostExecute
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}