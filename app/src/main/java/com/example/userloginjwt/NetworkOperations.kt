package com.example.userloginjwt

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

abstract class NetworkOperations {
    abstract fun doWorkBeforeBackground()
    abstract suspend fun doInBackground(): Response<ResponseBody>
    abstract suspend fun doWorkAfterBackground(result: Response<ResponseBody>)

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