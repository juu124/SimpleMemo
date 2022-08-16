package kr.co.witches.simple.memo.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import kr.co.witches.simple.memo.presentations.base.BaseOneDialogActivity
import kr.co.witches.simple.memo.presentations.base.BaseTwoDialogActivity

object CommonUtil {

    fun showAlertDialog1(
        context: Context,
        launcher: ActivityResultLauncher<Intent>?,
        title: String,
        message: String,
        button: String?
    ) {
        val intent = Intent(context, BaseOneDialogActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("title", title)
        intent.putExtra("message", message)
        button?.let { intent.putExtra("button", it) }
        if (launcher != null) {
            launcher.launch(intent)
        } else {
            context.startActivity(intent)
        }
    }

    fun showAlertDialog2(
        context: Context,
        launcher: ActivityResultLauncher<Intent>?,
        title: String,
        message: String,
        left: String?,
        right: String?
    ) {
        val intent = Intent(context, BaseTwoDialogActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("title", title)
        intent.putExtra("message", message)
        left?.let { intent.putExtra("left", it) }
        right?.let { intent.putExtra("right", it) }
        if (launcher != null) {
            launcher.launch(intent)
        } else {
            context.startActivity(intent)
        }
    }

    fun hideSoftInput(activity: Activity) {
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showSoftInput(edit: EditText, context: Context) {
        edit.isFocusable = true
        edit.isFocusableInTouchMode = true
        edit.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(edit, 0)
    }

    fun toggleSoftInput(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun px2dp(context: Context, px: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun dp2px(context: Context, dp: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}


