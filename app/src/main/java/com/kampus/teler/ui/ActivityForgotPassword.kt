package com.kampus.teler.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.kampus.teler.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityForgotPassword : AppCompatActivity() {
    private lateinit var forgotPasswordBinding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(forgotPasswordBinding.root)
        initActionBar()

        forgotPasswordBinding.tbForgotPass.setOnClickListener{
            finish()
        }

        forgotPasswordBinding.btnSendEmailForgotPwd.setOnClickListener{
            val email = forgotPasswordBinding.etEmailForgotPassword.text.toString().trim()
            if(email.isEmpty()){
                forgotPasswordBinding.etEmailForgotPassword.error = "please field your email"
                forgotPasswordBinding.etEmailForgotPassword.requestFocus()
                return@setOnClickListener
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                forgotPasswordBinding.etEmailForgotPassword.error = "please use valid email"
                forgotPasswordBinding.etEmailForgotPassword.requestFocus()
            }else{
                forgotPassword(email)
            }
        }
    }

    private fun forgotPassword(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener {task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Your reset password has been sent to your email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }else{
                Toast.makeText(this,"Failed reset password", Toast.LENGTH_SHORT).show()
            }
            }.addOnFailureListener {
                exception ->
                Toast.makeText(this,exception.message, Toast.LENGTH_SHORT).show()
            }

    }

    private fun initActionBar(){
        setSupportActionBar(forgotPasswordBinding.tbForgotPass)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}