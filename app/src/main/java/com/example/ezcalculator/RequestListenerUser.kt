package com.example.ezcalculator

class RequestListenerUser {
    lateinit var requestListener: RequestCallback
    fun useRequestListener(string: String) {
        requestListener.request(string)
    }
}