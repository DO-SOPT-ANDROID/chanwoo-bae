package org.sopt.dosopttemplate.ui.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.Friend
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) = with(binding) {
        imgProfile.setImageResource(friendData.profileImage)
        tvName.text = friendData.name
        tvSelfDescription.text = friendData.self_description
    }
}
