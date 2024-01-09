package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.MainActivity
import com.kampus.teler.R
import com.kampus.teler.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var resultBinding: ActivityResultBinding
//    private val predictedLabelAlergy = intent.getStringExtra(extra_label_alergy)

    companion object {
        const val extra_label_alergy= "extra_alergy"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)

        resultTitleSection()
        btnAction()
        descriptionText()


    }
    private fun descriptionText(){
        val predictedLabelAlergy = intent.getStringExtra(extra_label_alergy)

        val getNameAlergy = resources.getStringArray(R.array.resultTestNameAlergyList)
        val getDataDeskripsiALergy =resources.getStringArray(R.array.resultTestDescriptionAlergy)
        val getDataIndexAlergy =getNameAlergy.indexOf(predictedLabelAlergy)
        val deskripsiAlergy = getDataDeskripsiALergy.get(getDataIndexAlergy)


        val tvDeskripsi: TextView = resultBinding.deskripsiAlergy
        tvDeskripsi.text = deskripsiAlergy
        val tvHimbauan: TextView =resultBinding.himbauan
        tvHimbauan.text = "Penting untuk diingat bahwa pengobatan dan manajemen alergi harus dilakukan di bawah pengawasan dokter atau profesional kesehatan. Jika Anda memiliki kecurigaan terhadap alergi tertentu, disarankan untuk berkonsultasi dengan dokter untuk diagnosis dan rekomendasi pengobatan yang tepat."
        val tvAlert: TextView =resultBinding.alert
        tvAlert.text = "untuk menjaga kestabilan tubuh hindari sumber alergan ya !"

    }

    private fun resultTitleSection(){
        val predictedLabelAlergy = intent.getStringExtra(extra_label_alergy)

        val tvJudul: TextView = resultBinding.titleAlergy
        tvJudul.text= "Berdasarkan jawabanmu, Hasil test menunjukkan bahwa Anda mengalami $predictedLabelAlergy."
    }

    private fun btnAction(){
        val btn: Button = resultBinding.btnResultBackToHome
        btn.setOnClickListener{
            val intent = Intent (it.context, MainActivity::class.java)
            it.context?.startActivity(intent)
        }
    }
}