package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kampus.teler.MainActivity
import com.kampus.teler.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var callBackManager: CallbackManager
    companion object{
        const val RC_SIGN_IN = 0
    }

    private fun initFirebaseAth(){
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        callBackManager = CallbackManager.Factory.create();

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        initFirebaseAth()

        onAction()

    }


    private fun onAction() {
        val moveToHome = Intent(this, MainActivity::class.java)
        val moveToRegister = Intent(this,RegisterActivity::class.java)
        val moveToForgotPassword = Intent(this,ActivityForgotPassword::class.java)

        loginBinding.apply {
            btnLogin.setOnClickListener{
                val email = etEmailLogin.text.toString().trim()
                val pass = etPasswordLogin.text.toString().trim()
                if(checkValidation(email,pass)){
                    loginToServer(email,pass)
                }
            }
            btnRegister.setOnClickListener {
                startActivity(moveToRegister)
            }
            btnForgotPasswordLogin.setOnClickListener {
                startActivity(moveToForgotPassword)
            }
            btnLoginGoogle.setOnClickListener {
                val signIntent = mGoogleSignInClient.signInIntent
                startActivityForResult(signIntent, RC_SIGN_IN)
            }
            btnLoginFacebook.setOnClickListener {
                loginWithFacebook()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            CustomeDialog.showLoading(this)
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
                fireBaseAuth(credential)
            }catch (e: ApiException){
                CustomeDialog.hideLoading()
                Toast.makeText(this, "SIgn in cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loginToServer(email: String, pass: String) {
        val credential = EmailAuthProvider.getCredential(email,pass)
        fireBaseAuth(credential)

    }

    private fun fireBaseAuth(credential: AuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener{
            task ->
            if(task.isSuccessful){
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }else{
                Toast.makeText(this, "Sign in Failed", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            exception ->
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkValidation(email: String, pass: String): Boolean {
        if(email.isEmpty()){
            loginBinding.etEmailLogin.error = "Please field Your email"
            loginBinding.etEmailLogin.requestFocus()
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginBinding.etEmailLogin.error = "please enter valid email"
            loginBinding.etEmailLogin.requestFocus()
        }else if(pass.isEmpty()){
            loginBinding.etPasswordLogin.error = "Please field your password"
            loginBinding.etPasswordLogin.requestFocus()
        }else {
            return true
        }
        return false
    }

    private fun loginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callBackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                     CustomeDialog.showLoading(this@LoginActivity)
                    val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token.toString())
                    fireBaseAuth(credential)
                }

                override fun onCancel() {

                }

                override fun onError(exception: FacebookException) {
                   Toast.makeText(this@LoginActivity,"gagal login", Toast.LENGTH_SHORT).show()
                }
            })
    }
}

