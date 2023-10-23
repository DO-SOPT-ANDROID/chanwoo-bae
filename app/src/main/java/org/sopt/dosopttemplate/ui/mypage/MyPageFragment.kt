package org.sopt.dosopttemplate.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

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
        val receivedList = arguments?.getStringArrayList("userInputList")
        if (receivedList != null) {
            tvMainNick.text = receivedList[2].toString()
            tvIdData.text = receivedList[0].toString()
            tvNickData.text = receivedList[2].toString()
            tvMbtiData.text = receivedList[3].toString()
        } else {
            Log.d("nullcheck", "null")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
