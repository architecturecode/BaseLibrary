package com.base.library.base.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton
import com.afollestad.materialdialogs.actions.getActionButton
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import me.hgj.jetpackmvvm.demo.app.util.SettingUtil

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　:
 */


/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

}

fun AppCompatActivity.showMessage(
    message: String,
    title: String = "提示",
    positiveButtonText: String = "确定",
    positiveAction: () -> Unit = {},
    negativeButtonText: String = "",
    negativeAction: () -> Unit = {},
) {
    MaterialDialog(this).cancelable(true).lifecycleOwner(this).show {
        title(text = title)
        message(text = message)
        positiveButton(text = positiveButtonText) {
            positiveAction.invoke()
        }

        if (negativeButtonText.isNotEmpty()) {
            negativeButton(text = negativeButtonText) {
                negativeAction.invoke()
            }
        }

        getActionButton(WhichButton.POSITIVE).updateTextColor(SettingUtil.getColor(this@showMessage))
        getActionButton(WhichButton.NEGATIVE).updateTextColor(SettingUtil.getColor(this@showMessage))

    }
}