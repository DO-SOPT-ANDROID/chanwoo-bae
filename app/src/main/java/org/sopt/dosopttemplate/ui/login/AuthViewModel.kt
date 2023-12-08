package org.sopt.dosopttemplate.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.user.UserInfo
import org.sopt.dosopttemplate.network.ApiFactory.ServicePool.authService
import org.sopt.dosopttemplate.network.login.RequestLoginDto
import org.sopt.dosopttemplate.network.login.ResponseLoginDto

class AuthViewModel : ViewModel() {
    private val _loginResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    private val _isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoginButtonClicked: LiveData<Boolean>
        get() = _isLoginButtonClicked

    fun login(id: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.login(RequestLoginDto(id, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    _loginResult.value = it.body()
                    Log.d("server", _loginResult.value.toString())
                    UserInfo.userInfoList.nickName = it.body()?.username.toString()
                    UserInfo.userInfoList.id = it.body()?.id.toString()
                    _loginSuccess.value = true
                } else {
                    _loginSuccess.value = false
                    Log.d("server", it.code().toString())
                }
            }.onFailure {
                Log.d("server", it.message.toString())
            }
        }
    }

    fun onLoginButtonClick() {
        _isLoginButtonClicked.value = _isLoginButtonClicked.value != true
    }
}
