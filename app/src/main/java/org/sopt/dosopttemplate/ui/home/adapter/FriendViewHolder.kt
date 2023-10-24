package org.sopt.dosopttemplate.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HomeSealedItem.Friend) = with(binding) {
        imgProfile.setImageResource(friendData.profileImage)
        tvName.text = friendData.name
        tvSelfDescription.text = friendData.self_description
    }
}
