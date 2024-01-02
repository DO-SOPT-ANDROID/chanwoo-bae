package org.sopt.dosopttemplate.ui.doandroid

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.entity.ReqresEntity
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import org.sopt.dosopttemplate.utils.UiState

class DoAndroidViewModel(val reqresRepository: ReqresRepository) : ViewModel() {

    private val _reqresUserState = MutableStateFlow<UiState<List<ReqresEntity>>>(UiState.Loading)
    val reqresUserState get() = _reqresUserState.asStateFlow()

    fun getReqresUserList(page: Int) {
        viewModelScope.launch {
            reqresRepository.getReqresList(page).onSuccess {
                if (it.isNotEmpty()) {
                    val reqresUserList = it.map { entity ->
                        ReqresEntity(
                            id = entity.id,
                            avatar = entity.avatar,
                            email = entity.email,
                            first_name = entity.first_name,
                            last_name = entity.last_name,
                        )
                    }
                    _reqresUserState.value = UiState.Success(reqresUserList)
                } else {
                    Log.d("reqres", "비었음")
                }
            }.onFailure {
                Log.d("reqres", it.message.toString())
            }
        }
    }
}
