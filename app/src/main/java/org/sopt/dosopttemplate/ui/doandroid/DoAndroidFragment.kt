package org.sopt.dosopttemplate.ui.doandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding
import org.sopt.dosopttemplate.network.doandroid.ResponseReqresDto
import org.sopt.dosopttemplate.network.reqresApiFactory.ServicePool.reqresService
import org.sopt.dosopttemplate.ui.doandroid.adapter.CarouselHeroAdapter
import org.sopt.dosopttemplate.ui.doandroid.adapter.CarouselUserAdapter
import org.sopt.dosopttemplate.ui.home.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoAndroidFragment : Fragment() {
    private var _binding: FragmentDoAndroidBinding? = null

    private val viewModel by viewModels<HomeViewModel>()

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
        reqresService.getUserList(1).enqueue(object : Callback<ResponseReqresDto> {
            override fun onResponse(
                call: Call<ResponseReqresDto>,
                response: Response<ResponseReqresDto>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()!!

                    data.let {
                        carouselOriginalAdapter.setCarouselList(it.data)
                    }
                } else {
                    Log.d("userlist", "fail")
                }
            }

            override fun onFailure(call: Call<ResponseReqresDto>, t: Throwable) {
                Log.e("doandroid", "Error: ${t.message}")
            }
        })
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
