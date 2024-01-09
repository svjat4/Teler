package com.kampus.teler.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse (
    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("alergi")
    val alergi: String?,

    @field:SerializedName("deskripsi")
    val deskripsi: String?,

    @field:SerializedName("gambar")
    val gambar: String?,

    @field:SerializedName("gejala")
    val gejala: String?,

    @field:SerializedName("penyebab")
    val penyebab: String?,

    @field:SerializedName("solusi")
    val solusi: String?,


)


