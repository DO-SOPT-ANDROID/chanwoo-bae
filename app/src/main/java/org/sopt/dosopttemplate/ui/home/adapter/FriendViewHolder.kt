package org.sopt.dosopttemplate.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HomeSealedItem.Friend) = with(binding) {
        imgProfile.setImageResource(friendData.profileImage)
        tvName.text = friendData.name

        // 멜론 뮤직 없을 경우에 cardview또한 안보이도록
        if (friendData.self_description.isEmpty()) {
            cvGreen.visibility = View.INVISIBLE
        } else {
            tvSelfDescription.text = friendData.self_description
            cvGreen.visibility = View.VISIBLE
        }
    }
}
