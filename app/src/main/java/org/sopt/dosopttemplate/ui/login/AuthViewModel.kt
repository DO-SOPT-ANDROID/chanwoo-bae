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
import org.sopt.dosopttemplate.data.dto.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.dto.remote.respose.ResponseLoginDto
import org.sopt.dosopttemplate.data.repositoryimpl.AuthRepository
import org.sopt.dosopttemplate.ui.model.UserInfo
import org.sopt.dosopttemplate.utils.UiState

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginState = MutableStateFlow<UiState<ResponseLoginDto>>(UiState.Loading)
    val loginState: StateFlow<UiState<ResponseLoginDto>> get() = _loginState.asStateFlow()

    private val _isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoginButtonClicked: LiveData<Boolean>
        get() = _isLoginButtonClicked

    fun login(id: String, password: String) {
        viewModelScope.launch {
            authRepository.postLogin(RequestLoginDto(id, password))
                .onSuccess {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            _loginState.value = UiState.Success(response)
                            Log.d("server", _loginState.value.toString())
                            UserInfo.updateUserInfo(
                                id = response.id.toString(),
                                nickName = response.nickname.toString(),
                            )
                        }
                    } else {
                        _loginState.value = UiState.Error
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
