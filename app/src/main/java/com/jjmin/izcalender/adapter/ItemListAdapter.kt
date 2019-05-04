package com.jjmin.izcalender.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalender.R
import com.jjmin.izcalender.data.DetailPlanItem
import com.jjmin.izcalender.viewmodel.DetailViewModel
import com.jjmin.izcalender.databinding.ItemDetailBinding

class ItemListAdapter(private val vm: DetailViewModel) : ListAdapter<DetailPlanItem,ItemListAdapter.ViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding : ItemDetailBinding = DataBindingUtil.bind(view)!!
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<DetailPlanItem>() {
            override fun areItemsTheSame(oldItem: DetailPlanItem, newItem: DetailPlanItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem:DetailPlanItem, newItem: DetailPlanItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}