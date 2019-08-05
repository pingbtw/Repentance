package com.pingbtw.web


import com.google.gson.Gson
import spark.kotlin.*
import com.pingbtw.utilities.submitAppeal


data class RequestData(
        val id: String
)

data class Response (
        val code: Int = 200,
        val message: String = "Success"
)

fun initialize() {


    post("/hello") {
        val gson = Gson()
        var requestData: RequestData = gson.fromJson(request.body(), RequestData::class.java)
        println("Request received for ${requestData.id}")
        submitAppeal(requestData.id)
        gson.toJson(Response())
        }
}