package org.sopt.dosopttemplate.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.user.UserInfo
import org.sopt.dosopttemplate.network.ApiFactory.ServicePool.authService
import org.sopt.dosopttemplate.network.login.RequestLoginDto

class AuthViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoginButtonClicked: LiveData<Boolean>
        get() = _isLoginButtonClicked

    fun login(id: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.login(RequestLoginDto(id, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    val response = it.body()
                    if (response != null) {
                        _loginState.value = LoginState.Success(response)
                        Log.d("server", _loginState.value.toString())
                        UserInfo.userInfoList.nickName = response.username.toString()
                        UserInfo.userInfoList.id = response.id.toString()
                    }
                } else {
                    _loginState.value = LoginState.Error
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
