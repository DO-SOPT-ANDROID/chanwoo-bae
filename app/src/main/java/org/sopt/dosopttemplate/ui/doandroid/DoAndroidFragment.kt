package org.sopt.dosopttemplate.ui.doandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding
import org.sopt.dosopttemplate.ui.doandroid.adapter.CarouselAdapter
import org.sopt.dosopttemplate.ui.home.HomeViewModel

class DoAndroidFragment : Fragment() {
    private var _binding: FragmentDoAndroidBinding? = null

    private val viewModel by viewModels<HomeViewModel>()

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

        initCarouselRecyclerView()
    }

    private fun initCarouselRecyclerView() {
        val carouselAdapter = CarouselAdapter(requireContext())
        binding.carouselRecyclerView.adapter = carouselAdapter
        binding.carouselRecyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        carouselAdapter.setCarouselList(viewModel.mockBirthList)

        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.carouselRecyclerView)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
