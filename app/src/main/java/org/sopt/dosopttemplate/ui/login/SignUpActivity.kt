package org.sopt.dosopttemplate.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.ui.model.User
import org.sopt.dosopttemplate.utils.UiState
import org.sopt.dosopttemplate.utils.ViewModelFactory
import org.sopt.dosopttemplate.utils.toast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>() {
        ViewModelFactory()
    }

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

    private fun initSignUp() {
        binding.btnLogin.setOnClickListener {
            val userEntity = User(
                id = binding.editId.text.toString(),
                pwd = binding.editPwd.text.toString(),
                nickName = binding.editNickname.text.toString(),
                mbti = binding.editMbti.text.toString(),
            )
            viewModel.postSignUp(userEntity)
            observeSignUpState(userEntity)
        }
    }

    private fun observeSignUpState(userEntity: User) {
        lifecycleScope.launch {
            viewModel.signUpState.collect {
                when (it) {
                    is UiState.Success -> {
                        sendUserData(userEntity)
                        toast(getString(R.string.toast_signUp_compeleted))
                    }

                    is UiState.Error -> toast(getString(R.string.toast_signUp_fail))
                    is UiState.Loading -> toast("회원가입 중")
                }
            }
        }
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
