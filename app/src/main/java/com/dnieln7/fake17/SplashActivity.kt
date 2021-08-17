package com.dnieln7.fake17

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dnieln7.fake17.ui.auth.AuthActivity
import com.dnieln7.fake17.ui.home.HomeActivity
import com.dnieln7.fake17.ui.home.cat.CatsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Activity to display a screen with the app's logo while the session is being checked.
 *
 * @author dnieln7
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userDataSource = (application as Fake17Application).serviceLocator.userDataSource

        // If no user data start Auth Activity
        // If user data start HomeActivity
        // If error close the app
        userDataSource.getFirstUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                },
                {
                    Log.e(CatsViewModel::class.simpleName, "Error", it)
                    finish()
                },
                {
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
            )
    }
}