package org.sopt.dosopttemplate.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.ui.HomeActivity
import org.sopt.dosopttemplate.utils.ViewModelFactory
import org.sopt.dosopttemplate.utils.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>() {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LifeCycleOwner
        initDataBinding()
        // 로그인 버튼 클릭
        initLoginBtn()
        // 회원가입 버튼 클릭
        initSignUpBtn()
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.vm = authViewModel
    }

    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {
            val id = binding.editId.text.toString()
            val pwd = binding.editPwd.text.toString()

            authViewModel.login(id = id, password = pwd)
        }
        observeLoginResult()
    }

    private fun initSignUpBtn() {
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeLoginResult() {
        lifecycleScope.launch {
            authViewModel.loginState.collect { loginState ->
                when (loginState) {
                    is LoginState.Success -> {
                        toast("로그인 성공")
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }

                    is LoginState.Error -> toast("로그인 실패")
                    is LoginState.Loading -> toast("로그인 중")
                }
            }
        }
    }
}
