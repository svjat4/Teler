package com.kampus.teler.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kampus.teler.R
import com.kampus.teler.databinding.ActivityFragmentRiwayatBinding

class RiwayatFragment  : Fragment(R.layout.activity_fragment_riwayat) {

    private lateinit var riwayatFragment: RiwayatFragment
    private lateinit var riwayatBinding: ActivityFragmentRiwayatBinding
    private lateinit var riwayatViewModel: RiwayatViewModel

    lateinit var riwayatListRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        riwayatViewModel =
            ViewModelProvider(this).get(RiwayatViewModel::class.java)
        riwayatBinding = ActivityFragmentRiwayatBinding.inflate(layoutInflater)

        riwayatListRV = riwayatBinding.riwayatListView

        return  riwayatBinding.root
    }
}
