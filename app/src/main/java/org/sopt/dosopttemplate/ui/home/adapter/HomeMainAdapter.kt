package org.sopt.dosopttemplate.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemBirthdayBinding
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import org.sopt.dosopttemplate.databinding.ItemTitleLineBinding
import org.sopt.dosopttemplate.ui.home.adapter.viewholder.BirthdayViewHolder
import org.sopt.dosopttemplate.ui.home.adapter.viewholder.FriendViewHolder
import org.sopt.dosopttemplate.ui.home.adapter.viewholder.ProfileViewHolder
import org.sopt.dosopttemplate.ui.home.adapter.viewholder.TitleLineViewHolder
import java.lang.IllegalArgumentException

class HomeMainAdapter(context: Context) :
    ListAdapter<HomeSealedItem, RecyclerView.ViewHolder>(HomeDiffCallBack) {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 뷰 타입을 구별하기 위한 상수 선언.
    companion object {
        const val PROFILE_VIEW = 0
        const val FRIEND_VIEW = 1
        const val TITLE_LINE_VIEW = 2
        const val BIRTHDAY_VIEW = 3
    }

    // HomeSealedItem을 타입으로 가지는 items 리스트 선언.
    var items = listOf<HomeSealedItem>()
        set(value) {
            field = value
            submitList(field.toMutableList())
        }

    // 3. 상수에 따라서 ItemFriendBinding 레이아웃을 사용하여 아이템 뷰 홀더를 생성.
    // viewType은 getItemViewType 함수에서 결정된 값
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PROFILE_VIEW -> {
                val profileBinding = ItemMyProfileBinding.inflate(inflater, parent, false)
                ProfileViewHolder(profileBinding) // 프로필 뷰 홀더 생성
            }

            FRIEND_VIEW -> {
                val friendBinding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(friendBinding) // 친구 뷰 홀더 생성
            }

            TITLE_LINE_VIEW -> {
                val titleLineBinding = ItemTitleLineBinding.inflate(inflater, parent, false)
                TitleLineViewHolder(titleLineBinding) // 타이틀 라인 뷰 홀더 생성
            }

            BIRTHDAY_VIEW -> {
                val birthDayBinding = ItemBirthdayBinding.inflate(inflater, parent, false)
                BirthdayViewHolder(birthDayBinding) // 타이틀 라인 뷰 홀더 생성
            }

            else -> throw IllegalArgumentException("invalid item type")
        }
    }

    // 4. ViewHolder를 데이터와 바인딩 및 아이템 뷰 유형을 결정
    // holder의 유형에 따라 ProfileViewHolder 또는 FriendViewHolder로 형 변환하여 해당 뷰 홀더의 onBind 함수를 호출.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ProfileViewHolder -> holder.onBind(currentList[position] as HomeSealedItem.MyProfile)
            is FriendViewHolder -> holder.onBind(currentList[position] as HomeSealedItem.Friend)
            is TitleLineViewHolder -> holder.onBind(currentList[position] as HomeSealedItem.TitleLine)
            is BirthdayViewHolder -> holder.onBind(currentList[position] as HomeSealedItem.Birthday)
        }
    }

    // 1.
    override fun getItemCount() = currentList.size

    // 2. 특정 위치의 데이터의 뷰 타입을 반환.
    // items[position]을 통해 해당 위치의 아이템을 확인하고, 해당 아이템의 타입에 따라 PROFILE_VIEW 또는 FRIEND_VIEW를 반환
    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is HomeSealedItem.MyProfile -> PROFILE_VIEW
            is HomeSealedItem.Friend -> FRIEND_VIEW
            is HomeSealedItem.TitleLine -> TITLE_LINE_VIEW
            is HomeSealedItem.Birthday -> BIRTHDAY_VIEW
            else -> throw IllegalArgumentException("invalid item type")
        }
    }
}
