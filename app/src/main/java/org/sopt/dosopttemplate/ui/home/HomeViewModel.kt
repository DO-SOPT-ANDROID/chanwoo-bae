package org.sopt.dosopttemplate.ui.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.home.HomeSealedItem

class HomeViewModel : ViewModel() {

    val mockProfileList = listOf<HomeSealedItem.MyProfile>(
        HomeSealedItem.MyProfile(
            myProfileImg = R.drawable.gingerbread,
            myName = "배찬우",
            my_description = "멀티리사이클러뷰 어렵네",
        ),
    )

    val mockFriendList = listOf<HomeSealedItem.Friend>(
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "전성기 시절 파트장",
            self_description = "꼼짝마 손들어",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "손흥민",
            self_description = "나보다 잘생긴 사람 있으면 나와",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "표정 풀자",
        ),
    )
}
