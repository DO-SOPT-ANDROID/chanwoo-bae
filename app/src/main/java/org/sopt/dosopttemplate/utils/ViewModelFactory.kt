package org.sopt.dosopttemplate.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.ApiFactory
import org.sopt.dosopttemplate.data.datasourceimpl.remote.AuthDataSourceImpl
import org.sopt.dosopttemplate.data.datasourceimpl.remote.ReqresDataSourceImpl
import org.sopt.dosopttemplate.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.dosopttemplate.data.repositoryimpl.ReqresRepositoryImpl
import org.sopt.dosopttemplate.ui.doandroid.DoAndroidViewModel
import org.sopt.dosopttemplate.ui.login.AuthViewModel
import org.sopt.dosopttemplate.ui.login.SignUpViewModel

class ViewModelFactory :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                val repository =
                    AuthRepositoryImpl(AuthDataSourceImpl(ApiFactory.ServicePool.authService))
                AuthViewModel(repository) as T
            }

            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                val repository =
                    AuthRepositoryImpl(AuthDataSourceImpl(ApiFactory.ServicePool.authService))
                SignUpViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DoAndroidViewModel::class.java) -> {
                val repository =
                    ReqresRepositoryImpl(ReqresDataSourceImpl(ApiFactory.ServicePool.userService))
                DoAndroidViewModel(repository) as T
            }
            // Add more ViewModel cases as needed
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
