package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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

            arrayFlag[0] = id.length in 6..10
            arrayFlag[1] = pwd.length in 8..12
            arrayFlag[2] = nickName.length in 1..8
            arrayFlag[3] = mbti.length == 4

            if (arrayFlag.all { it }) {
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val snackList = mutableListOf<String>()
                if (!arrayFlag[0]) {
                    snackList.add("ID는 6자 이상 10자 이하여야 합니다.")
                }
                if (!arrayFlag[1]) {
                    snackList.add("비밀번호는 8자 이상 12자 이하여야 합니다.")
                }
                if (!arrayFlag[2]) {
                    snackList.add("닉네임은 1자 이상 8자 이하여야 합니다.")
                }
                if (!arrayFlag[3]) {
                    snackList.add("MBTI는 4자여야 합니다.")
                }

                val snackMessage = snackList.joinToString("\n")

                Snackbar.make(
                    root,
                    snackMessage,
                    Snackbar.LENGTH_SHORT,
                ).show()
            }
        }
    }
}
