package org.sopt.dosopttemplate.ui.model

import org.sopt.dosopttemplate.domain.entity.UserEntity

object UserInfo {
    var userInfoList = UserEntity(
        id = "",
        pwd = "",
        nickName = "",
        mbti = "",
    )

    fun updateUserInfo(
        id: String = userInfoList.id,
        pwd: String = userInfoList.pwd,
        nickName: String = userInfoList.nickName,
        mbti: String = userInfoList.mbti,
    ) {
        userInfoList = userInfoList.copy(id = id, pwd = pwd, nickName = nickName, mbti = mbti)
    }
}
