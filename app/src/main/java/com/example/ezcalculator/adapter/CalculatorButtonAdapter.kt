package com.example.ezcalculator.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcalculator.CalculateButtonListenerUser
import com.example.ezcalculator.R
import com.google.android.material.button.MaterialButton

private val normal = listOf(
    listOf("C", "<-", "%", "÷"),
    listOf("7", "8", "9", "/"),
    listOf("4", "5", "6", "*"),
    listOf("1", "2", "3", "+"),
    listOf("转换", "0", ".", "=")
)

private val science = listOf(
    listOf("2nd", "deg", "sin", "cos", "tan"),
    listOf("x^y", "lg", "ln", "(", ")"),
    listOf("√￣", "C", "<-", "%", "÷"),
    listOf("x!", "7", "8", "9", "/"),
    listOf("1/x", "4", "5", "6", "*"),
    listOf("π", "1", "2", "3", "+"),
    listOf("转换", "e", "0", ".", "=")
)
private var flag = false
private var data = normal

class CalculatorButtonAdapter(private var calculateButtonListenerUser: CalculateButtonListenerUser) :
    RecyclerView.Adapter<CalculatorButtonAdapter.ViewHolder>() {
    private lateinit var view: View

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: MaterialButton = itemView.findViewById(R.id.calbutton)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.digit_button, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.button.setOnClickListener {
            when (viewHolder.button.text) {
                "转换" -> {
                    if (!flag) {
                        flag = true
                        data = science
                        (parent as RecyclerView).layoutManager =
                            GridLayoutManager(parent.context, 5)
                        notifyDataSetChanged()
                    } else {
                        flag = false
                        data = normal
                        (parent as RecyclerView).layoutManager =
                            GridLayoutManager(parent.context, 4)
                        notifyDataSetChanged()
                    }
                }
            }
            calculateButtonListenerUser.useRequestListener(viewHolder.button.text.toString())
        }
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size * data[0].size

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buttonValue = data[position / data[0].size][position % data[0].size]
        holder.button.text = buttonValue
        holder.button.textSize = if (!flag) {
            30F
        } else {
            20F
        }
        holder.button.height = view.context.display?.height!! / 2 / data.size
    }
}
