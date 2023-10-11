package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // siginUp activity와 쌍방향 데이터 전달 콜백 함수
        initRegister()
        // 화면 전환
        initSignUp()
    }

    private fun initRegister() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val receivedList = data?.getStringArrayListExtra("userInputList")
                    if (receivedList != null) {
                        for (item in receivedList) {
                            Log.d("ReceivedData", item)
                        }
                    }
                    Toast.makeText(this, "Main 으로 돌아옴", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun initSignUp() {
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}
