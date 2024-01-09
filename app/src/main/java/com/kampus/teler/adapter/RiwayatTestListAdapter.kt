package com.kampus.teler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kampus.teler.R
import com.kampus.teler.model.RiwayatTest

class RiwayatTestListAdapter(private val listRiwayat: ArrayList<RiwayatTest>):RecyclerView.Adapter<RiwayatTestListAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: RiwayatTestListAdapter.OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatTestListAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat_test, parent, false)
        return RiwayatTestListAdapter.ListViewHolder(view)
    }

    override fun getItemCount(): Int = listRiwayat.size

    override fun onBindViewHolder(holder: RiwayatTestListAdapter.ListViewHolder, position: Int) {
        val (tglTest, namaTest, hasil ) = listRiwayat[position]
        holder.tglTest.text = tglTest
        holder.testName.text = namaTest
        holder.result.text = hasil
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listRiwayat[holder.adapterPosition])
        }
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tglTest : TextView =itemView.findViewById(R.id.testDate)
        val testName: TextView = itemView.findViewById(R.id.testName)
        val result: TextView = itemView.findViewById(R.id.testResult)
        val context: Context = itemView.context
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: RiwayatTest)
    }
}
