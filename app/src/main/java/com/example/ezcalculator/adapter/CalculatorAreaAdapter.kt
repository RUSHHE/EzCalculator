package com.example.ezcalculator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcalculator.RequestCallback
import com.example.ezcalculator.R
import com.example.ezcalculator.RequestListenerUser
import com.example.ezcalculator.utils.Deal

class CalculatorAreaAdapter(private var buttonRequestListenerUser: RequestListenerUser, private var historyRequestListenerUser: RequestListenerUser) :
    RecyclerView.Adapter<CalculatorAreaAdapter.ViewHolder>() {
    private var calculateItem = ArrayList<String>().apply {
        add("0")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.calc_area_tv)
    }

    private val deal = Deal()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calc_area_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.textView.setOnClickListener {
            Log.i("CalcArea", viewHolder.textView.toString())
            Log.i("CalcArea", calculateItem.toString())
        }
        buttonRequestListenerUser.requestListener = object : RequestCallback {
            override fun request(string: String) {
                when (string) {
                    "C" -> {
                        calculateItem = ArrayList<String>().apply {
                            add("0")
                        }
                        notifyDataSetChanged()
                    }

                    "<-" -> {
                        if (calculateItem[calculateItem.size - 1].length == 1) {
                            calculateItem[calculateItem.size - 1] = "0"
                        } else {
                            calculateItem[calculateItem.size - 1] =
                                calculateItem[calculateItem.size - 1].dropLast(1)
                        }
                        notifyItemChanged(getItemCount() - 1)
                    }

                    "=" -> {
                        calculateItem.add(
                            deal.calculate(calculateItem[calculateItem.size - 1]).toString()
                        )
                        notifyItemInserted(getItemCount() - 1)
                        historyRequestListenerUser.useRequestListener(calculateItem[calculateItem.size - 2] + "=" + calculateItem[calculateItem.size - 1])
                    }

                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "+", "-", "*", "/", "%" -> {
                        if (calculateItem[calculateItem.size - 1] == "0") {
                            calculateItem[calculateItem.size - 1] = string
                        } else {
                            calculateItem[calculateItem.size - 1] = calculateItem[calculateItem.size - 1].plus(string)
                        }
                        notifyItemChanged(getItemCount() - 1)
                    }

                    else -> {
                        Toast.makeText(parent.context, string, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return ViewHolder(view)
    }

    override fun getItemCount() = calculateItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = calculateItem.get(index = position)
    }
}