package com.application.demodeeplink.utils

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.application.demodeeplink.ui.base.BaseActivity
import com.application.demodeeplink.ui.base.CustomApplication
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.util.LinkProperties


private const val TAG = "AppUtil"

object AppUtil {

    fun preventTwoClick(view: View?) {
        if (view != null) {
            view.isEnabled = false
            view.postDelayed(Runnable { view.isEnabled = true }, 1000)
        }
    }

    fun showToast(msg: String?, isCenter: Boolean? = false) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(CustomApplication.getInstance(), msg, Toast.LENGTH_SHORT).apply {
                if (isCenter == true)
                    setGravity(Gravity.CENTER, 20, 20)
                show()
            }
        }
    }

    fun hideKeyboard(activity: Activity) {
        val view = (activity.findViewById<View>(R.id.content) as ViewGroup).getChildAt(0)
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getProfileLink(
        activity: BaseActivity,
        username: String?,
        buo: BranchUniversalObject?
    ): String? {
        val lp = LinkProperties()
            .setFeature("sharing")
            .addControlParameter("username", username)
            .setAlias(username) //This is used to show username in link instead of encoded value
        return buo?.getShortUrl(activity, lp) //link
    }

    fun shareProfileLink(
        activity: BaseActivity,
        link: String?
    ) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, link
        )
        sendIntent.type = "text/plain"
        val shareIntent = Intent.createChooser(sendIntent, null)
        activity.startActivity(shareIntent)
    }
}

