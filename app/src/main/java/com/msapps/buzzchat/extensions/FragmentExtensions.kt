package com.msapps.buzzchat.extensions

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showToolbar() {
    (requireActivity() as AppCompatActivity).supportActionBar?.show()
}

fun Fragment.hideToolbar() {
    (requireActivity() as AppCompatActivity).supportActionBar?.hide()
}

fun Fragment.showDialog(
    title: String,
    message: String?,
    positiveBtnText: String? = null,
    positiveBtnAction: ((dialog: DialogInterface, Int) -> Unit)? = null,
    negativeBtnText: String? = null,
    negativeBtnAction: ((dialog: DialogInterface, Int) -> Unit)? = null
) {
    MaterialAlertDialogBuilder(requireContext()).apply {
        setTitle(title)
        setMessage(message)
        setNegativeButton(negativeBtnText, negativeBtnAction)
        setPositiveButton(positiveBtnText, positiveBtnAction)
        show()
    }
}