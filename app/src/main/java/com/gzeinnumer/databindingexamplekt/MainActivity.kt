package com.gzeinnumer.databindingexamplekt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gzeinnumer.databindingexamplekt.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

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
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding!!.viewModel = MyViewModel2()
        initView()
        initOnClick()
        spesialRecyclerView()
    }

    private fun initView() {
        binding!!.btn.text = "Click To Open Dialog"
    }

    private fun initOnClick() {
        binding!!.btn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            val previous = supportFragmentManager.findFragmentByTag(MainDialog.TAG)
            if (previous != null) {
                transaction.remove(previous)
            }
            val dialog: DialogFragment = MainDialog.newInstance()
            dialog.show(transaction, MainDialog.TAG)
        }
    }

    private fun spesialRecyclerView() {
        val adapterData = AdapterRV()
        adapterData.setOnClick(object : AdapterRV.MyOnClick {
            override fun myOnClick(position: Int) {
                Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        val adapterMT = AdapterRVMultiType()
        adapterMT.setOnClick(object : AdapterRVMultiType.MyOnClick{
            override fun myOnClick(position: Int) {
                Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        val list = ArrayList<String>()
        list.add("RV 1")
        list.add("RV 2")
        binding!!.rv.adapter = adapterMT
        adapterMT.setList(list)
        binding!!.rv.hasFixedSize()
        binding!!.rv.layoutManager = LinearLayoutManager(this)
    }

    fun myOnClick(position: Int) {
        Toast.makeText(this@MainActivity, position.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}