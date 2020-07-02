package com.app.khazna_task.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import app.khazna_task.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    // todo vars
    var topAnim: Animation? = null
    var bottomAnim: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initAnim()
        initSplash()
    }

    // todo init animation
    private fun initAnim() {
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)
        imageView!!.animation = topAnim
        textView!!.animation = bottomAnim
    }

    // todo initSplash
    private fun initSplash() {
        val decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        decorView.systemUiVisibility = uiOptions
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, PostActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    } // initialize splash

    // todo onWindowFocusChanged
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    } // disable touch on splash screen

}