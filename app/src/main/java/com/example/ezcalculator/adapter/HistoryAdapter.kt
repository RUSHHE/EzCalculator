package com.example.ezcalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcalculator.HistoryAreaData
import com.example.ezcalculator.R

class HistoryAdapter(private val historyAreaDataList: List<HistoryAreaData>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.history_date_tv)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.history_item_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = historyAreaDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = historyAreaDataList[position].date

        val linearLayoutManager = LinearLayoutManager(holder.recyclerView.context)
        holder.recyclerView.layoutManager = linearLayoutManager
        holder.recyclerView.adapter = ChildAdapter(historyAreaDataList[position].itemList, position)
    }

    inner class ChildAdapter(private val itemList: List<String>, val parentIndex: Int) : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(R.id.history_child_item_tv)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.history_child_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = itemList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = itemList[position]
        }
    }
}