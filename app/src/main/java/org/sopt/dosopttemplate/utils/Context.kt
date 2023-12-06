package org.sopt.dosopttemplate.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.snackBar(anchorView: View, message: String) {
    Snackbar.make(anchorView, message, Snackbar.LENGTH_SHORT).show()
}
