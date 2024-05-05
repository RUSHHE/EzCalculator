package com.example.ezcalculator

class CalculateButtonListenerUser {
    lateinit var requestListener: CalculateButtonCallback
    fun useRequestListener(string: String) {
        requestListener.request(string)
    }
}