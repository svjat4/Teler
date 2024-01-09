package com.kampus.teler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kampus.teler.R
import com.kampus.teler.model.ArticleResponse


class SearchListAdapter(private val listArticle: ArrayList<ArticleResponse>): RecyclerView.Adapter<SearchListAdapter.ListViewHolder>()  {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listArticle.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (judul, description ) = listArticle[position]
        holder.judul.text = judul
        holder.description.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listArticle[holder.adapterPosition])
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val judul : TextView =itemView.findViewById(R.id.judul_alergi)
        val description: TextView = itemView.findViewById(R.id.description)
        val context: Context = itemView.context
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticleResponse)
    }
}