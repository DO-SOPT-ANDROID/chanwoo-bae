package org.sopt.dosopttemplate.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.login.User
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.network.ApiFactory.ServicePool.authService
import org.sopt.dosopttemplate.network.login.RequestSignUpDto
import org.sopt.dosopttemplate.utils.toast
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDataBinding()
        initSignUp()
        initHideKeyboard()
    }

    private fun initDataBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initSignUp() = with(binding) {
        btnLogin.setOnClickListener {
            val userEntity = User(
                id = editId.text.toString(),
                pwd = editPwd.text.toString(),
                nickName = editNickname.text.toString(),
                mbti = editMbti.text.toString(),
            )
            signUpServer(userEntity)
        }
    }

    private fun signUpServer(userEntity: User) {
        authService.signUp(RequestSignUpDto(userEntity.id, userEntity.pwd, userEntity.mbti))
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        sendUserData(userEntity)
                        toast(getString(R.string.toast_signUp_compeleted))
                    } else {
                        toast(getString(R.string.toast_signUp_fail))
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    toast(getString(R.string.toast_sever_error))
                    Log.e("SignUpActivity", "Error: ${t.message}")
                }
            })
    }

    private fun sendUserData(userEntity: User) {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        intent.putExtra(USER_TAG, userEntity)
        // LoginActivity로 결과를 반환
        setResult(RESULT_OK, intent)
        // 결과 반환 후 현재 액티비티 종료
        finish()
    }

    private fun initHideKeyboard() {
        // Check if no view has focus:
        // Only runs if there is a view that is currently focused
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object {
        const val USER_TAG = "userInputList"
    }
}
