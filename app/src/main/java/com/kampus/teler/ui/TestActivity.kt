package com.kampus.teler.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.Constants
import com.kampus.teler.databinding.ActivityTestBinding
import com.kampus.teler.model.TestQuetions
import com.kampus.teler.repository.TestHelperML
import com.google.gson.Gson

class TestActivity: AppCompatActivity() {
    private lateinit var testBinding: ActivityTestBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var context:Context
    private lateinit var gson: Gson
    private lateinit var textClassifier : TestHelperML

    private var answerTestList: ArrayList<Float> = arrayListOf()
    private val questionsTestList: ArrayList<TestQuetions> = Constants.getTestQuetion()
    private var currentTestQuestionIndex = 0

    private var tvTestQuestion: TextView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var btnOptionAnswer: ArrayList<Button>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(testBinding.root)

        onActionBtn()

        tvTestQuestion = testBinding.tvQuestion
        progressBar = testBinding.progressBarTest
        tvProgress = testBinding.tvProgress
        btnOptionAnswer = arrayListOf(testBinding.btnOption1,testBinding.btnOption2)


        val context = applicationContext
        textClassifier = TestHelperML(context)
        textClassifier.loadModel("alergy_model_1.tflite", "label.txt")


        btnOptionAnswer?.let {
            actionBtnOption(it)
        }
    }

    private fun actionBtnOption(button: ArrayList<Button>){
        for (optionIndex in button.indices) {
            button[optionIndex].let {
                it.setOnClickListener{
                    if (it == testBinding.btnOption1 ){
                        val answerTrue = 1
                        answerTestList.add(answerTrue.toFloat())
                    }else{
                        val answerFalse = 0
                        answerTestList.add(answerFalse.toFloat())
                    }

                    if (currentTestQuestionIndex < questionsTestList.size - 1) {
                        currentTestQuestionIndex++
                        updateTestQuestion()
                    }else{
                        moveToResult()
                    }
                }
            }
        }
    }


    private fun moveToResult(){
        val result = textClassifier.classifyPredictedALergy(answerTestList)

        val predictedLabel = getMaxValue(result)
        val intent = Intent (this, ResultActivity::class.java)

        if (predictedLabel != null) {
            val (key, value) = predictedLabel
            println("Label Alergy: $key, Max Value Score: $value")
            intent.putExtra(ResultActivity.extra_label_alergy, predictedLabel.key)
        } else {
            println("The data is empty.")
        }

        this?.startActivity(intent)
    }

    private fun getMaxValue(data: Map<String, Float>): Map.Entry<String, Float>? {
        return data.minByOrNull { it.value }
    }

    private fun updateTestQuestion() {
        tvTestQuestion?.text = questionsTestList[currentTestQuestionIndex].questionText

        progressBar?.progress = currentTestQuestionIndex + 1

        tvProgress?.text = "${currentTestQuestionIndex + 1}/${questionsTestList.size}"

        for (optionIndex in questionsTestList[currentTestQuestionIndex].optionAnswer.indices) {
            btnOptionAnswer!![optionIndex].text = questionsTestList[currentTestQuestionIndex].optionAnswer[optionIndex]
        }
    }


    private fun onActionBtn() {
        testBinding.apply {
            backButton.setOnClickListener{
                finish()
            }

        }
    }
}