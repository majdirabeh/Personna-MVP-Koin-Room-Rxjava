package fr.dev.majdi.personnamvproom.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Majdi RABEH on 30/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */

object Utils {

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            //e.printStackTrace()
        }
    }
}