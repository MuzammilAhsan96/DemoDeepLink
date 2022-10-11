package com.application.demodeeplink.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.application.demodeeplink.utils.AppUtil
import io.branch.indexing.BranchUniversalObject

abstract class BaseActivity : AppCompatActivity() {
    val buo: BranchUniversalObject? = BranchUniversalObject()
        .setCanonicalIdentifier("https://demo.com/12345")
        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
        .setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AppUtil.hideKeyboard(this)
    }


    open fun launchActivity(classType: Class<out BaseActivity>) {
        startActivity(Intent(this, classType))
    }


    open fun launchActivity(classType: Class<out BaseActivity>, view: View) {
        AppUtil.preventTwoClick(view)
        startActivity(Intent(this, classType))
    }


    open fun launchActivity(classType: Class<out BaseActivity>, bundle: Bundle) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    open fun launchActivity(bundle: Bundle, classType: Class<out BaseActivity>) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    open fun launchActivity(classType: Class<out BaseActivity>, bundle: Bundle, view: View) {
        AppUtil.preventTwoClick(view)
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    open fun launchActivityForResult(
        classType: Class<out BaseActivity>,
        requestCode: Int,
        bundle: Bundle
    ) {
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    open fun launchActivityForResult(
        classType: Class<out BaseActivity>,
        requestCode: Int,
        view: View?
    ) {
        AppUtil.preventTwoClick(view)
        val intent = Intent(this, classType)
        startActivityForResult(intent, requestCode)
    }

    open fun launchActivityForResult(
        classType: Class<out BaseActivity>,
        requestCode: Int,
        bundle: Bundle,
        view: View?
    ) {
        AppUtil.preventTwoClick(view)
        val intent = Intent(this, classType)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    open fun launchActivityForResult(classType: Class<out BaseActivity>, requestCode: Int) {
        val intent = Intent(this, classType)
        startActivityForResult(intent, requestCode)
    }
}