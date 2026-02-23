package com.sandycodes.dayzero.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth
import com.sandycodes.dayzero.R
import com.sandycodes.dayzero.databinding.ActivityMainBinding
import com.sandycodes.dayzero.ui.Auth.AuthFragment
import com.sandycodes.dayzero.ui.Home.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.black)

        splashScreen.setOnExitAnimationListener { splashView ->
            splashView.view.animate()
                .alpha(0f)
                .setDuration(200)
                .setStartDelay(400)
                .withEndAction {
                    splashView.remove()
                }
                .start()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLoggedIn = FirebaseAuth.getInstance().currentUser != null

        if (savedInstanceState == null){
            if (isLoggedIn) {
                Log.i("Start", "isLoggedIn = ${isLoggedIn}")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, HomeFragment())
                    .commit()
            } else {
                Log.i("Start", "isLoggedIn = ${isLoggedIn}")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, AuthFragment())
                    .commit()
            }
        }

    }
}