package com.application.demodeeplink.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.application.demodeeplink.ui.base.BaseActivity
import io.branch.referral.Branch
import io.branch.referral.BranchError
import org.json.JSONObject


class SplashScreenActivity : BaseActivity(), Branch.BranchReferralInitListener {

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Prevent restart of app when app in background and click on launcher icon
        if (!isTaskRoot) {
            finish();
            return;
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("BranchSDK_Tester", "initSession")
        Branch.sessionBuilder(this).withCallback(this).withData(this.intent.data).init()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.intent = intent
        Branch.sessionBuilder(this).withCallback(this).reInit()
    }

    override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
        if (error == null) {
            Log.i("BRANCH_SDK_REF", referringParams.toString())
            username = referringParams?.optString("username", "")
        } else {
            Log.e("BRANCH_SDK_REFF", error.message)
        }
        //Use this code when authentication is also there in your app
       /* val isLogin = PrefManager.getString(PrefManager.ACCESS_TOKEN)
        if (isLogin.equals("") || isLogin?.isEmpty() == true) {
            launchActivity(LoginActivity::class.java)
            finish()
        } else {
            handleDeepLinking()
        }*/
        //I am calling handleDeepLinking() directly just to demonstrate you all.
        handleDeepLinking()
    }

    private fun handleDeepLinking() {
        if (!username.isNullOrEmpty()) {
            //Going with deep link
            val bundle = Bundle()
            bundle.putString("USERNAME", username)
            bundle.putString("DEEPLINK", "1")
            launchActivity(MainActivity::class.java, bundle)
        } else {
            //Going normally
            val bundle = Bundle()
            bundle.putString("DEEPLINK", "0")
            launchActivity(MainActivity::class.java, bundle)
        }
        finish()
    }
}
