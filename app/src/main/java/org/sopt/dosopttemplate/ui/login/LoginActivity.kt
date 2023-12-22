package org.sopt.dosopttemplate.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.ui.HomeActivity
import org.sopt.dosopttemplate.utils.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()

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
        authViewModel.loginSuccess.observe(this) {
            if (it) {
                toast("로그인 성공")
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                toast("로그인 실패")
            }
        }
    }
}
