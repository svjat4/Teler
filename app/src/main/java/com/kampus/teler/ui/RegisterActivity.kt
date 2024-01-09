package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity  : AppCompatActivity() {
    private lateinit var registerBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        onAction()
    }

    private fun onAction() {
        val moveToLogin = Intent(this, LoginActivity::class.java)

        registerBinding.apply {
            btnCloseRegister.setOnClickListener{
                startActivity(moveToLogin)
            }

            btnRegister.setOnClickListener {
//                Toast.makeText(it.context, "success register", Toast.LENGTH_SHORT).show()
//                startActivity(moveToLogin)
                val email = etEmailRegister.text.toString().trim()
                val pass = etPasswordRegister.text.toString().trim()
                val confirmPass = etConfirmPasswordRegister.text.toString().trim()
                CustomeDialog.hideLoading()
                if(checkValidationRegist(email,pass, confirmPass)){
                    registerToServer(email,pass)
                }
            }
        }
    }

    private fun registerToServer(email: String, pass: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email,pass
        ).addOnCompleteListener { task ->
            CustomeDialog.showLoading(this@RegisterActivity)
            if(task.isSuccessful){
                Toast.makeText(this, "success register", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,LoginActivity::class.java))
                finishAffinity()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "authentication failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValidationRegist(email: String, pass: String, confirmPass: String): Boolean {
        if(email.isEmpty()){
            registerBinding.etEmailRegister.error = "Please field Your email"
            registerBinding.etEmailRegister.requestFocus()
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            registerBinding.etEmailRegister.error = "use valid email"
            registerBinding.etEmailRegister.requestFocus()
        }else if(pass.isEmpty()){
            registerBinding.etPasswordRegister.error = "Please field your password"
            registerBinding.etPasswordRegister.requestFocus()
        }else if(confirmPass.isEmpty()){
            registerBinding.etConfirmPasswordRegister.error = "please field confirm pass"
            registerBinding.etConfirmPasswordRegister.requestFocus()
        }
        return true
    }
}