package com.jjmin.izcalendar.adapter

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.DetailPlanItem
import com.jjmin.izcalendar.data.ListDataInterface
import com.jjmin.izcalendar.data.PlanningItem
import com.jjmin.izcalendar.data.TodayItem
import com.jjmin.izcalendar.ui.detailplan.DetailViewModel
import com.jjmin.izcalendar.databinding.ItemDetailBinding
import com.jjmin.izcalendar.databinding.ItemPlanningBinding
import com.jjmin.izcalendar.databinding.ItemPlanningTodayBinding
import kotlin.reflect.KType


class ItemListAdapter(private val vm: ViewModel) :
    ListAdapter<ListDataInterface, RecyclerView.ViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            1 -> {
                MainViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_planning,
                        parent,
                        false
                    )
                )
            }
            2->{
                TodayViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_planning_today,
                        parent,
                        false
                    )
                )
            }

            3 -> {
                DetailViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_detail,
                        parent,
                        false
                    )
                )
            }

            else -> DetailViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_detail,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> {
                val item = getItem(position) as PlanningItem
                (holder as MainViewHolder).binding.item = item
            }
            2->{
                val item = getItem(position) as TodayItem
                (holder as TodayViewHolder).binding.item = item
            }
            3 -> {
                val item = getItem(position) as DetailPlanItem
                (holder as DetailViewHolder).binding.item = item
            }
        }

    }

    override fun getItemId(position: Int): Long {
        return getItemId(position).hashCode().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getitem()
    }

    class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemDetailBinding = DataBindingUtil.bind(view)!!
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemPlanningBinding = DataBindingUtil.bind(view)!!
    }

    class TodayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemPlanningTodayBinding = DataBindingUtil.bind(view)!!
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ListDataInterface>() {
            override fun areItemsTheSame(oldItem: ListDataInterface, newItem: ListDataInterface): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListDataInterface, newItem: ListDataInterface): Boolean {
                return oldItem == newItem
            }
        }
    }
}