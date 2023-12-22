package org.sopt.dosopttemplate.data.user

import org.sopt.dosopttemplate.data.login.User

object UserInfo {
    var userInfoList = User(
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
