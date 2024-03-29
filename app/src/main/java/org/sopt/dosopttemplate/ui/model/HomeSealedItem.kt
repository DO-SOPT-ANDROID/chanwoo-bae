package org.sopt.dosopttemplate.ui.model

import androidx.annotation.DrawableRes

sealed class HomeSealedItem {

    data class TitleLine(
        val title: String,
    ) : HomeSealedItem()

    data class MyProfile(
        @DrawableRes val myProfileImg: Int,
        val myName: String,
        val my_description: String,
    ) : HomeSealedItem()

    data class Friend(
        @DrawableRes val profileImage: Int,
        val name: String,
        val self_description: String,
    ) : HomeSealedItem()

    data class Birthday(
        @DrawableRes val profileImage: Int,
        val name: String,
        val date: String,
        val self_description: String,
    ) : HomeSealedItem()
}
