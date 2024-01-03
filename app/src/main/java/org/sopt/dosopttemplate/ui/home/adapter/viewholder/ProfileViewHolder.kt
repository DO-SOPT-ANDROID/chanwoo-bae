package org.sopt.dosopttemplate.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import org.sopt.dosopttemplate.ui.model.HomeSealedItem

class ProfileViewHolder(private val binding: ItemMyProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(profileData: HomeSealedItem.MyProfile) = with(binding) {
        imgProfile.setImageResource(profileData.myProfileImg)
        tvName.text = profileData.myName
        tvSelfDescription.text = profileData.my_description
    }
}
