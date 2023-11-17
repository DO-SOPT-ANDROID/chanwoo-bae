package org.sopt.dosopttemplate.ui.home.adapter.viewholder

import DateUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.ItemBirthdayBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BirthdayViewHolder(private val binding: ItemBirthdayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(birthData: HomeSealedItem.Birthday) = with(binding) {
        imgProfile.setImageResource(birthData.profileImage)
        tvName.text = birthData.name

        val date = LocalDate.parse(birthData.date)
        val formatter = DateTimeFormatter.ofPattern("M월 d일")

        when (DateUtils.getDateOrder(birthData.date)) {
            0 -> {
                cvBirthViewVisibility()
                tvBirthDate.text = "오늘 ${date.format(formatter)}"
            }

            1 -> {
                cvBirthViewVisibility()
                tvBirthDate.text = "어제 ${date.format(formatter)}"
            }

            else -> {
                tvBirthDate.visibility = View.GONE
                cvRed.visibility = View.GONE
                // 멜론 뮤직 없을 경우에 cardview또한 안보이도록
                if (birthData.self_description.isEmpty()) {
                    cvGreen.visibility = View.GONE
                } else {
                    tvSelfDescription.text = birthData.self_description
                    cvGreen.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun ItemBirthdayBinding.cvBirthViewVisibility() {
        cvGreen.visibility = View.GONE
        cvRed.visibility = View.VISIBLE
    }
}
