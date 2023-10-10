package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUp()
    }

    private fun initSignUp() = with(binding) {
        val arrayFlag = booleanArrayOf(false, false, false, false)

        btnLogin.setOnClickListener {
            val id = editId.text.toString()
            val pwd = editPwd.text.toString()
            val nickName = editNickname.text.toString()
            val mbti = editMbti.text.toString()

//            if, else문 축약
            arrayFlag[0] = id.length in 6..10

            arrayFlag[1] = pwd.length in 8..12

            arrayFlag[2] = nickName.length in 1..8

            arrayFlag[3] = mbti.length == 4

            /*    for (i in arrayFlag.indices) {
                    Log.d("flag", i.toString() + arrayFlag[i].toString())
                }*/
        }
    }
}
