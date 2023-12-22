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
import org.sopt.dosopttemplate.data.login.User
import org.sopt.dosopttemplate.network.ApiFactory.ServicePool.authService
import org.sopt.dosopttemplate.network.login.RequestSignUpDto

class SignUpViewModel : ViewModel() {
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Loading)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    private val id = MutableLiveData<String>()
    private val password = MutableLiveData<String>()
    private val nickname = MutableLiveData<String>()
    private val mbti = MutableLiveData<String>()

    private var isIdValid = false
    private var isPasswordValid = false
    private var isNickNameValid = false
    private var isMbtiValid = false

    private val _isBtnSelected = MutableLiveData<Boolean>()
    val isBtnSelected: LiveData<Boolean>
        get() = _isBtnSelected

    val idError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val nickNameError = MutableLiveData<String>()
    val mbtiError = MutableLiveData<String>()

    private val idLengthRegex = ".{6,10}".toRegex()
    private val englishRegex = ".*[a-zA-Z].*".toRegex()
    private val numberRegex = ".*\\d.*".toRegex()

    private val pwdLengthRegex = ".{6,12}".toRegex()
    private val specialLetter = ".*[^a-zA-Z\\d].*".toRegex()

    private val nickNameLengthRegex = ".{4,10}".toRegex()
    private val mbtiRegex = "^[E|Iei][S|Nsn][T|Ftf][J|Pjp]$".toRegex()

    init {
        // ViewModel이 생성될 때 초기 값 설정
        _isBtnSelected.value = false
    }

    fun setID(id: String) {
        this.id.value = id
        validateId(id)
        updateBtnValidity()
    }

    fun setPwd(password: String) {
        this.password.value = password
        validatePassword(password)
        updateBtnValidity()
    }

    fun setNickName(input: String) {
        nickname.value = input
        validateNickName(input)
        updateBtnValidity()
    }

    fun setMbti(input: String) {
        mbti.value = input
        validateMbti(input)
        updateBtnValidity()
    }

    private fun validateId(id: String) {
        isIdValid = idLengthRegex.matches(id) && englishRegex.matches(id) && numberRegex.matches(id)
        idError.value = if (!isIdValid) "6자 이상 10자 이내\n영문과 숫자를 포함해야 합니다." else ""
    }

    private fun validatePassword(password: String) {
        isPasswordValid = pwdLengthRegex.matches(password) && englishRegex.matches(password) &&
            numberRegex.matches(password) && specialLetter.matches(password)
        passwordError.value =
            if (!isPasswordValid) "6자 이상 12자 이내\n영문, 숫자, 특수문자를 포함해야 합니다." else ""
    }

    private fun validateNickName(inputNickname: String) {
        isNickNameValid = nickNameLengthRegex.matches(inputNickname)
        nickNameError.value =
            if (!isNickNameValid) "4자 이상 10자 이내로 입력해야 합니다." else ""
    }

    private fun validateMbti(inputMbti: String) {
        isMbtiValid = mbtiRegex.matches(inputMbti)
        mbtiError.value =
            if (!isMbtiValid) "올바른 MBTI 형식이 아닙니다." else ""
    }

    private fun updateBtnValidity() {
        _isBtnSelected.value = isIdValid && isPasswordValid && isNickNameValid && isMbtiValid
    }

    fun signUpServer(userEntity: User) {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.signUp(
                    RequestSignUpDto(
                        userEntity.id,
                        userEntity.pwd,
                        userEntity.nickName,
                    ),
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    _signUpState.value = SignUpState.Success
                } else {
                    _signUpState.value = SignUpState.Error
                }
            }.onFailure {
                Log.e("SignUpActivity", "Error: ${it.message}")
            }
        }
    }
}
