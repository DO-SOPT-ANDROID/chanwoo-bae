package org.sopt.dosopttemplate.ui.doandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemCarouselIntroduceBinding

class CarouselOriginalAdapter(context: Context) :
    ListAdapter<HomeSealedItem.Friend, CarouselOriginalAdapter.CarouseViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    inner class CarouseViewHolder(private val binding: ItemCarouselIntroduceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSealedItem.Friend) {
            binding.imgCarousel.setImageResource(data.profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouseViewHolder {
        val binding = ItemCarouselIntroduceBinding.inflate(inflater, parent, false)
        return CarouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouseViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    // submitList 사용
    fun setCarouselList(imgList: List<HomeSealedItem.Friend>) {
        submitList(imgList.toMutableList())
    }

    // diffUtill callback
    companion object {
        private var diffUtil = object : DiffUtil.ItemCallback<HomeSealedItem.Friend>() {
            override fun areItemsTheSame(
                oldItem: HomeSealedItem.Friend,
                newItem: HomeSealedItem.Friend,
            ): Boolean {
                return oldItem.profileImage == newItem.profileImage
            }

            override fun areContentsTheSame(
                oldItem: HomeSealedItem.Friend,
                newItem: HomeSealedItem.Friend,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
