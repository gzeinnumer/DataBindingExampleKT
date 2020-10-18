package com.gzeinnumer.databindingexamplekt

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.gzeinnumer.databindingexamplekt.databinding.FragmentMainDialogBinding


class MainDialog : DialogFragment() {
    var binding: FragmentMainDialogBinding? = null
    override fun onStart() {
        super.onStart()
        if (dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            dialog!!.window!!.setBackgroundDrawableResource(R.drawable.mygzn_background_dialog)

            //agar tidak bisa diclick luar
            dialog!!.setCanceledOnTouchOutside(false)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main_dialog)
        binding!!.viewModel = MyViewModel3()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

    private fun initView() {
        binding!!.title.text = "Disini Pesannya"
    }

    private fun initOnClick() {
        binding!!.btnOke.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        })
        binding!!.btnCancel.setOnClickListener(View.OnClickListener {
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
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
        const val TAG = "MainDialog"
        fun newInstance(): MainDialog {
            return MainDialog()
        }
    }
}