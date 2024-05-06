package com.example.ezcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezcalculator.adapter.CalculatorAreaAdapter
import com.example.ezcalculator.adapter.CalculatorButtonAdapter
import com.example.ezcalculator.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculateButtonListenerUser = CalculateButtonListenerUser()

        val areaLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.historyArea.layoutManager = areaLayoutManager
        val calcAreaAdapter = CalculatorAreaAdapter(calculateButtonListenerUser)
        binding.historyArea.adapter = calcAreaAdapter


        val buttonLayoutManager = object : GridLayoutManager(this, 4) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.buttonArea.layoutManager = buttonLayoutManager
        val calcButtonAdapter = CalculatorButtonAdapter(calculateButtonListenerUser)
        binding.buttonArea.adapter = calcButtonAdapter
    }
}
