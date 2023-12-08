package org.sopt.dosopttemplate.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val id = MutableLiveData<String>()
    private val password = MutableLiveData<String>()
    private val nickname = MutableLiveData<String>()
    private val mbti = MutableLiveData<String>()

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
        idError.value = when {
            !idLengthRegex.matches(id) -> "6자 이상 10자 이하여야 합니다."
            !englishRegex.matches(id) -> MSG_ERROR_ENGLISH
            !numberRegex.matches(id) -> MSG_ERROR_NUM
            else -> ""
        }
    }

    private fun validatePassword(password: String) {
        passwordError.value = when {
            !pwdLengthRegex.matches(password) -> "6자 이상 12자 이하여야 합니다"
            !englishRegex.matches(password) -> MSG_ERROR_ENGLISH
            !numberRegex.matches(password) -> MSG_ERROR_NUM
            !specialLetter.matches(password) -> "특수 문자를 포함하여야 합니다."
            else -> ""
        }
    }

    private fun validateNickName(inputNickname: String) {
        nickNameError.value =
            if (nickNameLengthRegex.matches(inputNickname)) "" else "닉네임은 4자 이상 10자 이하여야 합니다."
    }

    private fun validateMbti(inputMbti: String) {
        mbtiError.value =
            if (mbtiRegex.matches(inputMbti)) "" else "올바른 mbti를 입력하시오."
    }

    private fun updateBtnValidity() {
        _isBtnSelected.value =
            idError.value.isNullOrEmpty() &&
            passwordError.value.isNullOrEmpty() &&
            nickNameError.value.isNullOrEmpty() &&
            mbtiError.value.isNullOrEmpty()
    }

    companion object {
        private const val MSG_ERROR_ENGLISH = "영문을 포함하여야 합니다."
        private const val MSG_ERROR_NUM = "숫자를 포함하여야 합니다."
    }
}
