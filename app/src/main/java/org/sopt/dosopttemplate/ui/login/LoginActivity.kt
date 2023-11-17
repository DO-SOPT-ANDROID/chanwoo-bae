package org.sopt.dosopttemplate.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.data.user.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.network.ApiFactory.ServicePool.authService
import org.sopt.dosopttemplate.network.login.RequestLoginDto
import org.sopt.dosopttemplate.network.login.ResponseLoginDto
import org.sopt.dosopttemplate.ui.HomeActivity
import retrofit2.Call
import retrofit2.Response

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

            authLogin(id, pwd)
        }
    }

    private fun authLogin(id: String, pwd: String) {
        authService.login(RequestLoginDto(id, pwd))
            .enqueue(
                object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            val userId = data.id
                            Toast.makeText(
                                this@LoginActivity,
                                "로그인이 성공하였고 유저의 ID는 $userId 입니둥",
                                Toast.LENGTH_SHORT,
                            ).show()
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            showSnackMessage("아이디와 패스워드가 일치 하지 않습니다.")
                            Log.e("LoginActivity", "Error: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
                        Log.e("LoginActivity", "Error: ${t.message}")
                    }
                },

            )
    }

    private fun sendUserData(receivedList: ArrayList<String>) {
        val intentLogin = Intent(this@LoginActivity, HomeActivity::class.java)
        UserInfo.userInfoList = receivedList
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
