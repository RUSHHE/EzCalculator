package com.example.ezcalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcalculator.HistoryAreaData
import com.example.ezcalculator.R
import com.example.ezcalculator.RequestCallback
import com.example.ezcalculator.RequestListenerUser
import java.text.SimpleDateFormat
import java.util.Date

class HistoryAdapter(private var historyRequestListenerUser: RequestListenerUser) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private var historyAreaDataList = ArrayList<HistoryAreaData>().apply {
        add(HistoryAreaData(SimpleDateFormat("yyyy.MM.dd").format(Date(System.currentTimeMillis())).toString(),
            ArrayList()
        ))
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.history_date_tv)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.history_item_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        val viewHolder = ViewHolder(view)
        historyRequestListenerUser.requestListener = object : RequestCallback {
            override fun request(string: String) {
                val historyAreaData = historyAreaDataList.get(index = historyAreaDataList.size - 1)
                if (SimpleDateFormat("yyyy.MM.dd").format(Date(System.currentTimeMillis())).toString() == historyAreaData.date) {
                    historyAreaData.itemList.add(string)
                    notifyItemChanged(historyAreaDataList.size - 1)
                } else {
                    historyAreaDataList.add(HistoryAreaData(SimpleDateFormat("yyyy.MM.dd").format(Date(System.currentTimeMillis())).toString(),
                        ArrayList()
                    ))
                    historyAreaDataList[historyAreaDataList.size - 1].itemList.add(string)
                    notifyItemInserted(historyAreaDataList.size - 1)
                }
            }
        }
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