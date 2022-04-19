package com.bagicode.adsadvance.ui.splash

import android.os.Handler
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.changePage
import com.bagicode.adsadvance.databinding.ActivitySplashscreenBinding
import com.bagicode.adsadvance.ui.auth.AuthActivity

class SplashscreenActivity : BaseBindingActivity() {

    private lateinit var binding : ActivitySplashscreenBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        Handler().postDelayed({
            changePage(AuthActivity::class.java, null, true)

        }, 3000)
    }

}