package org.sopt.dosopttemplate.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.entity.UserRequestEntity
import org.sopt.dosopttemplate.domain.repository.AuthDomainRepository
import org.sopt.dosopttemplate.utils.UiState

class AuthViewModel(private val authRepository: AuthDomainRepository) : ViewModel() {
    private val _loginState = MutableSharedFlow<UiState<UserEntity>>()
    val loginState: SharedFlow<UiState<UserEntity>> get() = _loginState.asSharedFlow()

    private val _isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoginButtonClicked: LiveData<Boolean>
        get() = _isLoginButtonClicked

    fun login(id: String, password: String) {
        viewModelScope.launch {
            authRepository.login(UserRequestEntity(id, password))
                .onSuccess {
                    _loginState.emit(UiState.Success(it))
                }
                .onFailure {
                    Log.d("server", it.message.toString())
                    _loginState.emit(UiState.Error)
                }
        }
    }

    fun onLoginButtonClick() {
        _isLoginButtonClicked.value = _isLoginButtonClicked.value != true
    }
}
