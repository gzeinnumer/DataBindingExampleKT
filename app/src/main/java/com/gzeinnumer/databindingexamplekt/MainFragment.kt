package com.gzeinnumer.databindingexamplekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gzeinnumer.databindingexamplekt.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    /*
    android {
        untuk android versi di bawah 4
        dataBinding {
            enabled = true
        }

        untuk android versi 4 -> gradel versi 6.1.1 -> android gradle plugin version 4.0.0
        buildFeatures{
            dataBinding = true
        }
    }
    */
    private var binding: FragmentMainBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main)
        binding!!.viewModel = MyViewModel()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}