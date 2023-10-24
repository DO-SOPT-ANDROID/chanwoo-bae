package org.sopt.dosopttemplate.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import java.lang.IllegalArgumentException

class HomeMainAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 상수 선언
    companion object {
        const val PROFILE_VIEW = 0
        const val FRIEND_VIEW = 1
    }

    var items = listOf<HomeSealedItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PROFILE_VIEW -> {
                val profileBinding = ItemMyProfileBinding.inflate(inflater, parent, false)
                ProfileViewHolder(profileBinding)
            }

            FRIEND_VIEW -> {
                val friendBinding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(friendBinding)
            }

            else -> throw IllegalArgumentException("invalid item type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ProfileViewHolder -> holder.onBind(items[position] as HomeSealedItem.MyProfile)
            is FriendViewHolder -> holder.onBind(items[position] as HomeSealedItem.Friend)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeSealedItem.MyProfile -> PROFILE_VIEW
            is HomeSealedItem.Friend -> FRIEND_VIEW
            else -> throw IllegalArgumentException("invalid item type")
        }
    }

    // 리스트 연결
    /* @SuppressLint("NotifyDataSetChanged")
     fun setItemList(items: List<HomeRecyclerViewItem>) {
         this.items = items.toList()
         notifyDataSetChanged()
     }*/
}
