package org.sopt.dosopttemplate.ui.doandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemCarouselBinding

class CarouselAdapter(context: Context) :
    ListAdapter<HomeSealedItem.Birthday, CarouselAdapter.CarouseViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    inner class CarouseViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSealedItem.Birthday) {
            binding.imgCarousel.setImageResource(data.profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouseViewHolder {
        val binding = ItemCarouselBinding.inflate(inflater, parent, false)
        return CarouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouseViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    // submitList 사용
    fun setCarouselList(imgList: List<HomeSealedItem.Birthday>) {
        submitList(imgList.toMutableList())
    }

    // diffUtill callback
    companion object {
        private var diffUtil = object : DiffUtil.ItemCallback<HomeSealedItem.Birthday>() {
            override fun areItemsTheSame(
                oldItem: HomeSealedItem.Birthday,
                newItem: HomeSealedItem.Birthday,
            ): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(
                oldItem: HomeSealedItem.Birthday,
                newItem: HomeSealedItem.Birthday,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
