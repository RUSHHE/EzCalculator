package com.example.ezcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
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
        binding.buttonArea.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: android.graphics.Rect,
                view: android.view.View,
                parent: androidx.recyclerview.widget.RecyclerView,
                state: androidx.recyclerview.widget.RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.left = 10
                outRect.right = 10
            }
        })
    }
}
