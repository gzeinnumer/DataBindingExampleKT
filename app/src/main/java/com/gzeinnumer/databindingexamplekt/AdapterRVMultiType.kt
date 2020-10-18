package com.gzeinnumer.databindingexamplekt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gzeinnumer.databindingexamplekt.databinding.ItemAdapterRvBinding
import java.util.*


class AdapterRVMultiType : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_NORMAL) {
            val binding: ItemAdapterRvBinding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.viewModel = MyViewModel4()
            MyHolder(binding)
        } else {
            val binding: ItemAdapterRvBinding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.viewModel = MyViewModel4()
            MyHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_NORMAL) {
            (holder as MyHolder).bindData(list[position], onClick)
        } else {
            throw IllegalStateException("Unexpected value: " + holder.itemViewType)
        }
    }

    override fun getItemCount(): Int {
        return Math.max(list.size, 0)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position != -1) {
            TYPE_NORMAL
        } else {
            0
        }
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(data: String, onClick: MyOnClick) {
            binding.text.text = data
            binding.text.setOnClickListener { onClick.myOnClick(adapterPosition) }
        }

    }

    companion object {
        private const val TYPE_NORMAL = 1
    }

    init {
        list = ArrayList()
    }
}
