package com.kampus.teler.model

import com.google.gson.annotations.SerializedName

data class RiwayatTest (
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("testDate")
    val tanggalTest: String,

    @field:SerializedName("namaTest")
    val prediksi: String,

    @field:SerializedName("Hasil")
    val description: String,

    val typeOfAllergen: String,
    val notes: String?

)