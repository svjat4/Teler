package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kampus.teler.adapter.SearchListAdapter
import com.kampus.teler.databinding.ActivitySearchBinding
import com.kampus.teler.model.ArticleResponse

class SearchActivity: AppCompatActivity() {
    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var searchListAdapter: SearchListAdapter
    private lateinit var allergyList: ArrayList<ArticleResponse>
    private var filteredlist: ArrayList<ArticleResponse> = ArrayList()


    lateinit var alergyListRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        alergyListRV = searchBinding.rvListArticle

        allergyList = ArrayList()
//        searchListAdapter = SearchListAdapter(allergyList)
//
//        alergyListRV.adapter = searchListAdapter
//
//        searchBinding.rvListArticle.layoutManager = LinearLayoutManager(this)

        allergyList.add(ArticleResponse(
            "Hewan",
            "Hewan",
            "Gejala Hewan",
            "Penyebab Hewan",
            "Penanganan Hewan",
            "",
            ""
        ),)
        allergyList.add( ArticleResponse(
            "Debu/Tungau",
            "Debu",
            "Gejala Debu/Tungau",
            "Penyebab Debu/Tungau",
            "Penanganan Debu/Tungau",
            "",
            ""
        ),)
        allergyList.add( ArticleResponse(
            "Makanan",
            "Makanan",
            "Gejala Makanan",
            "Penyebab Makanan",
            "Penanganan Makanan",
            "",
            ""
        ))
        onActionButton()
//        viewListArticle(filteredlist)
        onSearch()

    }


     fun onSearch(){
        val searchView = searchBinding.searchViewIdsArticle

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(msg: String?): Boolean {
                if (msg != null) {
                    filter(msg)
                }
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {

                return false
            }
        })
    }

    private fun filter(text: String) {
        for (item in allergyList) {
            if (item.alergi!!.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)

                println("data filter $filteredlist")
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        }else{
            viewListArticle(filteredlist)
        }
    }

    private fun viewListArticle(msg: ArrayList<ArticleResponse>){
        val listArticle =  ArrayList<ArticleResponse>()
        for(data in msg )

            listArticle.add(
                ArticleResponse(
                    judul =  "${data.judul}",
                    alergi = "${data.alergi}",
                    deskripsi = "${data.deskripsi}",
                    gambar = null,
                    gejala = " ${data.gejala}",
                    penyebab = null,
                    solusi = null
                )
            )
        searchBinding.rvListArticle.layoutManager = LinearLayoutManager(this)

        val adapter = SearchListAdapter(listArticle)
        searchBinding.rvListArticle.adapter = adapter

        adapter.setOnItemClickCallback(object: SearchListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleResponse) {
                selectedItemArticleMovedToDetailArticle(data)
            }
        })
    }

    private fun onActionButton() {
        searchBinding.apply {
            btnCloseSearch.setOnClickListener {
                finish()
            }
        }
    }

    private fun createMockData(): ArrayList<ArticleResponse> {
        // TODO: Buat mock data alergi menggunakan model Alergi
        return arrayListOf(
            ArticleResponse(
                "Hewan",
                "Hewan",
                "Gejala Hewan",
                "Penyebab Hewan",
                "Penanganan Hewan",
                "",
                ""
            ),
            ArticleResponse(
                "Debu/Tungau",
                "Debu",
                "Gejala Debu/Tungau",
                "Penyebab Debu/Tungau",
                "Penanganan Debu/Tungau",
                "",
                ""
            ),
            ArticleResponse(
                "Makanan",
                "Makanan",
                "Gejala Makanan",
                "Penyebab Makanan",
                "Penanganan Makanan",
                "",
                ""
            ),
            ArticleResponse(
                "Obat",
                "Obat",
                "Gejala Obat",
                "Penyebab Obat",
                "Penanganan Obat",
                "",
                ""
            ),
            ArticleResponse(
                "Serbuk Sari",
                "Serbuk Sari",
                "Gejala Serbuk Sari",
                "Penyebab Serbuk Sari",
                "Penanganan Serbuk Sari",
                "",
                ""
            ),
            ArticleResponse(
                "Suhu Udara (dingin/panas)",
                "Deskripsi Suhu Udara",
                "Gejala Suhu Udara",
                "",
                "",
                "",
                ""
            )
        )
    }


    private fun selectedItemArticleMovedToDetailArticle(article: ArticleResponse){
        val goToDetailArticle = Intent(this@SearchActivity, DetailItemSearchArticleActivity::class.java)
        Log.e("main ", "article ${article.gejala}")

        startActivity(goToDetailArticle)
    }
}