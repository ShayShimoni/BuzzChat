package com.msapps.buzzchat.splash.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.msapps.buzzchat.MainActivity
import com.msapps.buzzchat.R
import com.msapps.buzzchat.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    private val viewModel: SplashActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        viewModel.checkLoginStatus()
        lifecycleScope.launch {
            viewModel.loginState.collectLatest { isLoggedIn ->
                delay(2000)
                withContext(Dispatchers.Main) {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java).apply {
                        putExtra(Constants.EXTRA_IS_LOGGED_IN, isLoggedIn)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}