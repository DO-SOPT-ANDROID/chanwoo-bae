package org.sopt.dosopttemplate.ui.login

import org.sopt.dosopttemplate.network.login.ResponseLoginDto

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: ResponseLoginDto) : LoginState()
    object Error : LoginState()
}
