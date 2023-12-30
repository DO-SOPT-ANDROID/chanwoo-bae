package org.sopt.dosopttemplate.ui.model

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
