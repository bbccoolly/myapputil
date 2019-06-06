package com.lcz.mua.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lcz.mua.databinding.ModelRecyclerItemPlantBinding
import com.lcz.mua.entity.PlantEntity

/**
 * desc TODO
 * Created by lcz on 2019/6/5.
 */
class PlantAdapter : ListAdapter<PlantEntity, PlantAdapter.ViewHolder>(PlantDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        holder.apply {
            bind(createOnClickListener(plant.plantId), plant)
            itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ModelRecyclerItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }


    private fun createOnClickListener(plantId: String): View.OnClickListener {
        return View.OnClickListener {

        }
    }


    class ViewHolder(private val binding: ModelRecyclerItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: PlantEntity) {
            binding.apply {
                clickListener = listener
                plant = item
                executePendingBindings()
            }
        }
    }

}

private class PlantDiffCallBack : DiffUtil.ItemCallback<PlantEntity>() {
    override fun areItemsTheSame(oldItem: PlantEntity, newItem: PlantEntity): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: PlantEntity, newItem: PlantEntity): Boolean {
        return oldItem == newItem
    }

}
