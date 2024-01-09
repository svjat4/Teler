package com.kampus.teler.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kampus.teler.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment: Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var profileFragment: ProfileFragment
    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var profileVieModel: ProfileVieModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileVieModel =
            ViewModelProvider(this).get(ProfileVieModel::class.java)
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        initFirebaseAuth()
        getData()
        val btnLogout: Button = profileBinding.btnLogoutUser
        btnLogout.setOnClickListener{
            auth.signOut()
            val intent = Intent (activity, LoginActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
//
//
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        return profileBinding.root
    }

    private fun getData() {
        val user = auth.currentUser
        if (user!= null){
            profileBinding.tvEmailUser.text = user.email
        }
    }

    private fun initFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }
}