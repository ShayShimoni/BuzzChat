package com.msapps.buzzchat.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.msapps.buzzchat.R
import kotlinx.coroutines.Runnable

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            Runnable {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            3000
        )
    }
}