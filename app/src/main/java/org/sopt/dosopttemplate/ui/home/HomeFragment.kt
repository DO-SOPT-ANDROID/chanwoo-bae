package org.sopt.dosopttemplate.ui.home

import DateUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.data.home.HomeSealedItem
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.ui.home.adapter.HomeMainAdapter
import java.time.LocalDate

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 객체 생성" }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        // 전체 멀티뷰 item 리스트 초기화
        val homeItemsList = homeSealedItems()

        // adapter 초기화
        val homeAdapter = HomeMainAdapter(requireContext())
        binding.rvFriends.adapter = homeAdapter
        homeAdapter.items = homeItemsList
    }

    // sealedItem과 viewModel의 더미데이터 결합
    private fun homeSealedItems(): MutableList<HomeSealedItem> {
        val homeItemsList = mutableListOf<HomeSealedItem>()

        // 프로필 추가
        homeItemsList.addAll(viewModel.mockProfileList)
        // 생일인 사람 리스트 추가
        addBirthList(homeItemsList)
        // 친구 리스트 추가
        addFriendList(homeItemsList)

        return homeItemsList
    }

    private fun addBirthList(homeItemsList: MutableList<HomeSealedItem>) {
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        // 오늘 어제 생일인 사람만 필터링
        val filteredBirthList = viewModel.mockBirthList.filter {
            if (it.date.isNotEmpty()) {
                val date = LocalDate.parse(it.date, DateUtils.dateFormatter)
                date == today || date == yesterday
            } else {
                false
            }
        }

        // 오늘 어제 순으로 생일 비교 후 리스트 정렬
        val sortedBirthList = filteredBirthList.sortedWith(
            compareBy({
                DateUtils.getDateOrder(it.date)
            }, { it.date }),
        )

        homeItemsList.add(HomeSealedItem.TitleLine("생일인 친구"))
        homeItemsList.addAll(sortedBirthList)
    }

    private fun addFriendList(homeItemsList: MutableList<HomeSealedItem>) {
        homeItemsList.add(HomeSealedItem.TitleLine("친구"))
        homeItemsList.addAll(viewModel.mockBirthList)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
