package org.sopt.dosopttemplate.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemTitleLineBinding

class TitleLineViewHolder(private val binding: ItemTitleLineBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(titleData: HomeSealedItem.TitleLine) {
        binding.tvTitle.text = titleData.title
    }
}
