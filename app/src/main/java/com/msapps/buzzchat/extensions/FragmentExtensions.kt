package com.msapps.buzzchat.extensions

import android.content.DialogInterface
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.msapps.buzzchat.R

fun Fragment.safeNavigation(@IdRes destinationId: Int, onNavigate: () -> Unit) {
    if (findNavController().currentDestination?.id == destinationId) {
        onNavigate()
    }
}

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

fun Fragment.showErrorDialog(t: Throwable) {
    showDialog(
        title = getString(R.string.error),
        message = getString(R.string.error_msg, t.message),
        positiveBtnText = getString(R.string.ok)
    )
}