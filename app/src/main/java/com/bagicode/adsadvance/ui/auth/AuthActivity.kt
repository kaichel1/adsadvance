package com.bagicode.adsadvance.ui.auth

import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.changePage
import com.bagicode.adsadvance.BagicodeTravel

import com.bagicode.adsadvance.databinding.ActivityAuthBinding
import com.bagicode.adsadvance.ui.main.MainActivity

class AuthActivity : BaseBindingActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {

//        Log.v("tamvan", "ini data " + BagicodeTravelBooking.getApp().getToken())
//        Log.v("tamvan", "ini data " + BagicodeTravel.getApp().getUser())
//        if (!BagicodeTravel.getApp().getToken().isNullOrEmpty()) {
//            changePage(FirstActivity::class.java, null, true)
//        }
        if (!BagicodeTravel.getApp().getToken().isNullOrEmpty()){
            changePage(MainActivity::class.java, null, true)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}