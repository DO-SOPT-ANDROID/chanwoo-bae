package org.sopt.dosopttemplate.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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

        initHideKeyboard()
    }

    private fun initHideKeyboard() {
        // Check if no view has focus:
        // Only runs if there is a view that is currently focused
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun initSignUp() = with(binding) {
        val arrayFlag = booleanArrayOf(false, false, false, false)

        btnLogin.setOnClickListener {
            // input List에 저장
            val userInputList = listOf(
                editId.text.toString(),
                editPwd.text.toString(),
                editNickname.text.toString(),
                editMbti.text.toString(),
            )
            // if else문 축약
            arrayFlag[0] = userInputList[0].length in 6..10
            arrayFlag[1] = userInputList[1].length in 8..12
            arrayFlag[2] = userInputList[2].length in 1..8
            arrayFlag[3] = userInputList[3].length == 4

            // 모든 조건이 true인 경우 로그인 화면으로 이동
            if (arrayFlag.all { it }) {
                sendUserData(userInputList)
            } else {
                // 추후에 textwathcer로 변경
                showSnackMessage(arrayFlag)
            }
        }
    }

    private fun sendUserData(userInputList: List<String>) {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        intent.putStringArrayListExtra("userInputList", ArrayList(userInputList))
        // LoginActivity로 결과를 반환
        setResult(RESULT_OK, intent)
        Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
        // 결과 반환 후 현재 액티비티 종료
        finish()
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
