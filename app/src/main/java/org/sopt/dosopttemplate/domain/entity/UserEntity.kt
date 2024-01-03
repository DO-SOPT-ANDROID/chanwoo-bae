package org.sopt.dosopttemplate.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val id: String,
    val pwd: String,
    val nickName: String,
    val mbti: String,
) : Parcelable
