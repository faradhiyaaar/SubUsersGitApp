package com.faradhy.subusersgitapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.faradhy.subusersgitapp.R
import com.faradhy.subusersgitapp.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        actionBar?.hide()
        val iv_note = findViewById<ImageView>(R.id.iv_note)
        iv_note.alpha=0f
        iv_note.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}