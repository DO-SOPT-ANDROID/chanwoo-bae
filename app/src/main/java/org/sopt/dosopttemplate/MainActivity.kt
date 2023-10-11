package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGetData()
    }

    private fun initGetData() = with(binding) {
        val receivedList = intent.getStringArrayListExtra("userInputList")
        if (receivedList != null) {
            tvMainNick.text = receivedList[2].toString()
            tvIdData.text = receivedList[0].toString()
            tvNickData.text = receivedList[2].toString()
            tvMbtiData.text = receivedList[3].toString()
        }
    }
}
