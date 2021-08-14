package com.dnieln7.fake17.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

object NavigationUtils {
    fun NavDirections.navigate(view: View) {
        view.findNavController().navigate(this)
    }
}