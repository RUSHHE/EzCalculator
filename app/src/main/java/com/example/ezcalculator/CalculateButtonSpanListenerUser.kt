package com.example.ezcalculator

class CalculateButtonSpanListenerUser {
    lateinit var requestSpanListener: CalculateButtonSpanCallback
    fun useRequestListener() {
        requestSpanListener.request()
    }
}