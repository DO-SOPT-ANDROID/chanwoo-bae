package org.sopt.dosopttemplate.ui.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.ui.model.HomeSealedItem
import org.sopt.dosopttemplate.ui.model.UserInfo

class HomeViewModel : ViewModel() {

    val mockProfileList = listOf<HomeSealedItem.MyProfile>(
        HomeSealedItem.MyProfile(
            myProfileImg = R.drawable.gingerbread,
            myName = UserInfo.userInfoList.nickName.ifEmpty { "닉네임" },
            my_description = "상태메시지 +",
        ),
    )

    val mockBirthList = listOf<HomeSealedItem.Birthday>(
        HomeSealedItem.Birthday(
            profileImage = R.drawable.gingerbread,
            name = "서재원",
            self_description = "",
            date = "2023-10-22",
        ),
        HomeSealedItem.Birthday(
            profileImage = R.drawable.gingerbread,
            name = "손명지",
            self_description = "aaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz",
            date = "2023-10-24",
        ),
        HomeSealedItem.Birthday(
            profileImage = R.drawable.gingerbread,
            name = "최민영",
            self_description = "안녕안녕",
            date = "2023-10-26",
        ),
        HomeSealedItem.Birthday(
            profileImage = R.drawable.gingerbread,
            name = "배찬우",
            self_description = "ㅁㅁㅁㅁ",
            date = "2023-10-25",
        ),
        HomeSealedItem.Birthday(
            profileImage = R.drawable.gingerbread,
            name = "전성기 시절 파트장",
            self_description = "꼼짝마 손들어zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz",
            date = "2023-01-15",
        ),
        HomeSealedItem.Birthday(
            profileImage = R.drawable.gingerbread,
            name = "손흥민",
            self_description = "ㅁㅁㅁㅁ",
            date = "2023-04-16",
        ),
    )

    val mockFriendList = listOf<HomeSealedItem.Friend>(
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "전성기 시절 파트장",
            self_description = "꼼짝마 손들어zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "손흥민",
            self_description = "나보다 잘생긴 사람 있으면 나와zzzzzzzzzzzzzzzz",
        ),
        HomeSealedItem.Friend(
            profileImage = R.drawable.gingerbread,
            name = "파트장",
            self_description = "",
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
