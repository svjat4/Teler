package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.MainActivity
import com.kampus.teler.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delayGoToLogin()
    }

    private fun checkAuth() {
        if (FirebaseAuth.getInstance().currentUser !=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun delayGoToLogin(){
     Handler().postDelayed({
//         val moveToHome = Intent(this,LoginActivity::class.java)
//         startActivity(moveToHome)
//         finish()
         checkAuth()
     }, 1000)
 }
}