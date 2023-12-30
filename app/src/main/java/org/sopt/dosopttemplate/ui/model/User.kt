package org.sopt.dosopttemplate.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String,
    var pwd: String,
    var nickName: String,
    var mbti: String,
) : Parcelable
