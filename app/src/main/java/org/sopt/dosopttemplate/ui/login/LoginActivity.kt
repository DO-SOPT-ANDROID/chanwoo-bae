package org.sopt.dosopttemplate.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.ui.MyPageActivity
import java.util.ArrayList

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 버튼 클릭
        initLoginBtn(ArrayList())
        // siginUp activity와 쌍방향 데이터 전달 콜백 함수
        initRegister()
        // 회원가입 버튼 클릭
        initSignUpBtn()
    }

    private fun initLoginBtn(receivedList: ArrayList<String>) = with(binding) {
        btnLogin.setOnClickListener {
            val id = editId.text.toString()
            val pwd = editPwd.text.toString()
            if (receivedList.isEmpty()) {
                showSnackMessage("회원가입을 먼저 진행해 주세요")
            } else if (id == receivedList[0] && pwd == receivedList[1]) {
                sendUserData(receivedList)
                finish()/* editId.text = null
                 editPwd.text = null*/
            } else {
                showSnackMessage("ID와 password가 일치하지 않습니다")
            }
        }
    }

    private fun sendUserData(receivedList: ArrayList<String>) {
        val intentLogin = Intent(this@LoginActivity, MyPageActivity::class.java)
        intentLogin.putStringArrayListExtra(USER_INPUT, receivedList)
        startActivity(intentLogin)
    }

    private fun initRegister() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val receivedList = data?.getStringArrayListExtra(USER_INPUT)
                    if (receivedList != null) {
                        initLoginBtn(receivedList)
                    }
                }
            }
    }

    private fun initSignUpBtn() {
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun showSnackMessage(msg: String) {
        Snackbar.make(
            binding.root,
            msg,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    //    상수 선언
    companion object {
        private const val USER_INPUT = "userInputList"
    }
}
