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
//            input List에 저장
            val userInputList = listOf(
                editId.text.toString(),
                editPwd.text.toString(),
                editNickname.text.toString(),
                editMbti.text.toString(),
            )
//            if else문 축약
            arrayFlag[0] = userInputList[0].length in 6..10
            arrayFlag[1] = userInputList[1].length in 8..12
            arrayFlag[2] = userInputList[2].length in 1..8
            arrayFlag[3] = userInputList[3].length == 4

//            모든 조건이 true인 경우 로그인 화면으로 이동
            if (arrayFlag.all { it }) {
                sendUserData(userInputList)
            } else {
//                추후에 textwathcer로 변경
                showSnackMessage(arrayFlag)
            }
        }
    }

    private fun sendUserData(userInputList: List<String>) {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        intent.putStringArrayListExtra("userInputList", ArrayList(userInputList))
        startActivity(intent)
    }

    private fun showSnackMessage(arrayFlag: BooleanArray) {
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
            binding.root,
            snackMessage,
            Snackbar.LENGTH_SHORT,
        ).show()
    }
}
