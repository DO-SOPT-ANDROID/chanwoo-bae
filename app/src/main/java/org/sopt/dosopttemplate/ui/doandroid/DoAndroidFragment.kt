package org.sopt.dosopttemplate.ui.doandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding
import org.sopt.dosopttemplate.ui.doandroid.adapter.CarouselHeroAdapter
import org.sopt.dosopttemplate.ui.doandroid.adapter.CarouselUserAdapter
import org.sopt.dosopttemplate.ui.home.HomeViewModel
import org.sopt.dosopttemplate.utils.UiState
import org.sopt.dosopttemplate.utils.ViewModelFactory

class DoAndroidFragment : Fragment() {
    private var _binding: FragmentDoAndroidBinding? = null

    private val viewModel by viewModels<HomeViewModel>()

    private val doAndroidViewModel: DoAndroidViewModel by viewModels {
        ViewModelFactory()
    }

    private lateinit var carouselOriginalAdapter: CarouselUserAdapter

    private val binding: FragmentDoAndroidBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDoAndroidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeroCarousel()
        initReqresCarousel()
        initUserApi()
    }

    private fun initUserApi() {
        doAndroidViewModel.getReqresUserList(1)
        lifecycleScope.launch {
            doAndroidViewModel.reqresUserState.collect {
                when (it) {
                    is UiState.Success -> {
                        val reqresData = it.data
                        carouselOriginalAdapter.setCarouselList(reqresData)
                    }

                    is UiState.Error -> {
                        Toast.makeText(requireContext(), "서버 연결실패", Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Loading -> Toast.makeText(
                        requireContext(),
                        "로딩 중",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }

    private fun initHeroCarousel() = with(binding) {
        val carouselAdapter = CarouselHeroAdapter(requireContext())
        carouselHeroView.adapter = carouselAdapter
        carouselHeroView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        carouselAdapter.setCarouselList(viewModel.mockBirthList)

        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(carouselHeroView)
    }

    private fun initReqresCarousel() = with(binding) {
        carouselOriginalAdapter = CarouselUserAdapter(requireContext())
        carouselOriginalView.adapter = carouselOriginalAdapter
        carouselOriginalView.layoutManager = CarouselLayoutManager()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
