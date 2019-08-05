package com.pingbtw.repentance.web


import com.google.common.eventbus.Subscribe
import com.google.gson.Gson
import com.pingbtw.repentance.services.Configuration
import com.pingbtw.repentance.utilities.DatabaseUtility
import spark.kotlin.*
import me.aberrantfox.kjdautils.api.annotation.Service


data class RequestData(
        val id: String
)

data class Response(
        val code: Int = 200,
        val message: String = "Success"
)

@Service
class RepentanceApi(
        val configuration: Configuration
) {
    fun initialize() {


        post("/hello") {
            val gson = Gson()
            var requestData: RequestData = gson.fromJson(request.body(), RequestData::class.java)
            println("Request received for ${requestData.id}")
            DatabaseUtility(configuration).submitAppeal(requestData.id)
            gson.toJson(Response())
        }
    }
}