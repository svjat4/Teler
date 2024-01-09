package com.kampus.teler.model

data class TestQuetions(
    val id: Int,
    val questionText: String,
    val optionAnswer: ArrayList<String>,
    val answers: ArrayList<Int>,
)
