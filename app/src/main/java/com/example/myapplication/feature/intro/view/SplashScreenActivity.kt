package com.example.myapplication.feature.intro.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.myapplication.core.base.BaseActivity
import com.example.myapplication.databinding.ActivitySplashScreenBinding
import com.example.myapplication.feature.main.view.MovieActivity

/**
 * @author Andika Bratadirja
 * @date 28/07/2023
 */
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun getViewBinding(): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {


        Handler().postDelayed({
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}