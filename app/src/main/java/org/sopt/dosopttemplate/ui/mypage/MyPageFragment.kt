package org.sopt.dosopttemplate.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.ui.model.UserInfo

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "하하하" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetData()
    }

    private fun initGetData() = with(binding) {
        val receivedList = UserInfo.userInfoList
        if (receivedList != null) {
            tvMainNick.text = receivedList.nickName
            tvIdData.text = receivedList.id
            tvNickData.text = receivedList.nickName
            tvMbtiData.text = receivedList.mbti
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
