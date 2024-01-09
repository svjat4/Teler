package com.kampus.teler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kampus.teler.R
import com.kampus.teler.model.ArticleResponse

class SearchAdapter2(
    private var alergyList: ArrayList<ArticleResponse>,
) : RecyclerView.Adapter<SearchAdapter2.AlergyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlergyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_search,
            parent, false
        )

        return AlergyViewHolder(itemView)
    }

    fun filterList(filterlist: ArrayList<ArticleResponse>) {

        alergyList = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchAdapter2.AlergyViewHolder, position: Int) {
        holder.alergyNameTV.text = alergyList.get(position).alergi
        holder.deskripdiALergi.text = alergyList.get(position).alergi

    }

    override fun getItemCount(): Int {
        return alergyList.size
    }

    class AlergyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alergyNameTV: TextView = itemView.findViewById(R.id.judul_alergi)
        val deskripdiALergi: TextView = itemView.findViewById(R.id.description)
    }
}