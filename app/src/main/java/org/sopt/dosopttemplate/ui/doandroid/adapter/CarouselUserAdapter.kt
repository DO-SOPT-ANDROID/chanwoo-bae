package org.sopt.dosopttemplate.ui.doandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseReqresDto
import org.sopt.dosopttemplate.databinding.ItemCarouselIntroduceBinding

class CarouselUserAdapter(context: Context) :
    ListAdapter<ResponseReqresDto.Data, CarouselUserAdapter.CarouseViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    inner class CarouseViewHolder(private val binding: ItemCarouselIntroduceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseReqresDto.Data) {
            Glide.with(itemView)
                .load(data.avatar)
                .into(binding.imgCarousel)
            binding.textCarousel.text = "${data.first_name} ${data.last_name}"
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
    fun setCarouselList(imgList: List<ResponseReqresDto.Data>) {
        submitList(imgList.toMutableList())
    }

    // diffUtill callback
    companion object {
        private var diffUtil = object : DiffUtil.ItemCallback<ResponseReqresDto.Data>() {
            override fun areItemsTheSame(
                oldItem: ResponseReqresDto.Data,
                newItem: ResponseReqresDto.Data,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseReqresDto.Data,
                newItem: ResponseReqresDto.Data,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
