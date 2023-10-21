package org.sopt.dosopttemplate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.home.Friend
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 생성" }

    private val mockFriendList = ArrayList<Friend>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        가짜 데이터 추가
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))
        mockFriendList.add(Friend(R.drawable.gingerbread, "손흥민", "안녕하세요"))

        val friendAdapter = FriendAdapter(requireContext())
        binding.rvFriends.adapter = friendAdapter
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
