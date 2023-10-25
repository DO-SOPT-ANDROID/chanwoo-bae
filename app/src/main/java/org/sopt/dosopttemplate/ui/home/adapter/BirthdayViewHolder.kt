package org.sopt.dosopttemplate.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemBirthdayBinding

class BirthdayViewHolder(private val binding: ItemBirthdayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(birthData: HomeSealedItem.Birthday) = with(binding) {
        imgProfile.setImageResource(birthData.profileImage)
        tvName.text = birthData.name
        tvBirthDate.text = DateUtils.formatCustomDate(birthData.date)

        // 멜론 뮤직 없을 경우에 cardview또한 안보이도록
        if (birthData.self_description.isEmpty()) {
            cvGreen.visibility = View.INVISIBLE
        } else {
            tvSelfDescription.text = birthData.self_description
            cvGreen.visibility = View.VISIBLE
        }
    }

    /* private fun formatCustomDate(inputDate: String): String {
         // 입력된 날짜를 LocalDate로 파싱
         val date = LocalDate.parse(inputDate)

         val today = LocalDate.now()
         val yesterday = today.minusDays(1)

         // 원하는 날짜 형식을 정의 (예: "M월 d일")
         val formatter = DateTimeFormatter.ofPattern("M월 d일")

         // LocalDate를 지정된 형식으로 포맷팅
         return when (date) {
             today -> "오늘 ${date.format(formatter)}"
             yesterday -> "어제 ${date.format(formatter)}"
             else -> date.format(formatter)
         }
     }*/
}
