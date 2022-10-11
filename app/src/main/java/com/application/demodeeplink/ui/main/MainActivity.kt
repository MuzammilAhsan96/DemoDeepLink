package com.application.demodeeplink.ui.main

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.application.demodeeplink.ui.base.BaseActivity
import com.application.demodeeplink.utils.AppUtil


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.application.demodeeplink.R.layout.activity_main)
        val myButton = findViewById<ConstraintLayout>(com.application.demodeeplink.R.id.const_main)
        myButton.setOnClickListener {
            AppUtil.preventTwoClick(it)
            val link = AppUtil.getProfileLink(this, "Muzammil", buo)
            AppUtil.showToast(link)
            AppUtil.shareProfileLink(this, link)
        }
    }
}