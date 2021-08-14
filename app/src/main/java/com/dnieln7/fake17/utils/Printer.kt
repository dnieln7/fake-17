package com.dnieln7.fake17.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Helper class to print messages to the UI
 *
 * @author dnieln7
 */
object Printer {
    /**
     * Displays a [Toast] for short period of time.
     *
     * @param context Activity context.
     * @param message Message to be displayed.
     */
    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a [Snackbar] for short period of time.
     *
     * @param view    The view in witch to show the message.
     * @param message Message to be displayed.
     */
    fun snackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}