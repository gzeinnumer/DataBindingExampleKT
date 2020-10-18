package com.gzeinnumer.databindingexamplekt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gzeinnumer.databindingexamplekt.databinding.ItemAdapterRvBinding
import java.util.*

class AdapterRV : RecyclerView.Adapter<AdapterRV.MyHolder>() {
    private var list = ArrayList<String>()
    private lateinit var onClick: MyOnClick
    fun setOnClick(onClick: MyOnClick) {
        this.onClick = onClick
    }

    interface MyOnClick {
        fun myOnClick(position: Int)
    }

    fun setList(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addList(data: String) {
        list.add(data)
        notifyItemChanged(list.size - 1) // untuk dinamis recyclerview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding: ItemAdapterRvBinding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.viewModel = MyViewModel4()
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindData(list[position], onClick)
    }

    override fun getItemCount(): Int {
        return list.size.coerceAtLeast(0)
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(data: String?, onClick: MyOnClick?) {
            binding.text.text = data
            if (onClick != null) {
                binding.text.setOnClickListener { onClick.myOnClick(adapterPosition) }
            }
        }

    }

    init {
        list = ArrayList()
    }
}