package com.kampus.teler.model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.kampus.teler.Constants

class TestViewModel: ViewModel()  {
    private lateinit var sharedPreferences: SharedPreferences
    private var currentQuestionIndex: Int = 0
    private val questionsList: ArrayList<TestQuetions> = Constants.getTestQuetion()
}