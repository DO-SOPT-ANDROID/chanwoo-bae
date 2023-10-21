package org.sopt.dosopttemplate.ui.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.home.Friend

class HomeViewModel : ViewModel() {

    val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.gingerbread,
            name = "전성기 시절 파트장",
            self_description = "꼼짝마 손들어",
        ),
        Friend(
            profileImage = R.drawable.gingerbread,
            name = "손흥민",
            self_description = "나보다 잘생긴 사람 있으면 나와",
        ),
        Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
    )
}
