package org.sopt.dosopttemplate.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import org.sopt.dosopttemplate.data.home.HomeSealedItem

object HomeDiffCallBack : DiffUtil.ItemCallback<HomeSealedItem>() {
    override fun areItemsTheSame(oldItem: HomeSealedItem, newItem: HomeSealedItem): Boolean {
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(oldItem: HomeSealedItem, newItem: HomeSealedItem): Boolean {
        return oldItem == newItem
    }
}
