package org.sopt.dosopttemplate.ui.login

sealed class SignUpState {
    object Loading : SignUpState()
    object Success : SignUpState()
    object Error : SignUpState()
}
