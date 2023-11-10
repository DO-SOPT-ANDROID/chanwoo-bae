package org.sopt.dosopttemplate.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import org.sopt.dosopttemplate.data.home.HomeSealedItem

object HomeDiffCallBack : DiffUtil.ItemCallback<HomeSealedItem>() {

    //    이거 왜 javaClass로 비교해야하는지 아시는 분 리뷰 달아주세요..!
    override fun areItemsTheSame(oldItem: HomeSealedItem, newItem: HomeSealedItem): Boolean {
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(oldItem: HomeSealedItem, newItem: HomeSealedItem): Boolean {
        return oldItem == newItem
    }
}
