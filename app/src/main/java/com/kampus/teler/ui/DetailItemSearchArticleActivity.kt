package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.databinding.ActivityDetailItemArticleBinding


class DetailItemSearchArticleActivity  : AppCompatActivity() {
    private lateinit var detailItemArticleBinding: ActivityDetailItemArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailItemArticleBinding = ActivityDetailItemArticleBinding.inflate(layoutInflater)
        setContentView(detailItemArticleBinding.root)
        detailItemArticleBinding.btnArrowBack.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
            finish()
        }
    }
}
