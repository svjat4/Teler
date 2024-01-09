package com.kampus.teler.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kampus.teler.R
import com.kampus.teler.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val btnTest: Button = homeBinding.btnTest//root.findViewById(R.id.btn_test)
        btnTest.setOnClickListener{
           val intent = Intent (activity, OnboardTestActivity::class.java)
           activity?.startActivity(intent)
       }

        val btnToSearch: Button = homeBinding.btnToSearch //root.findViewById(R.id.btn_to_search)
        btnToSearch.setOnClickListener{
            val intent = Intent (activity, SearchActivity::class.java)
            activity?.startActivity(intent)
        }

        return  homeBinding.root
    }
}
