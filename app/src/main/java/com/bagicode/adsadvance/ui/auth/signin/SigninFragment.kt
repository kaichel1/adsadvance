package com.bagicode.adsadvance.ui.auth.signin

import android.util.Log
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.changePage
import com.google.gson.Gson
import com.bagicode.adsadvance.BagicodeTravel
import com.bagicode.adsadvance.databinding.FragmentSigninBinding
import com.bagicode.adsadvance.model.Response.LoginResponse
import com.bagicode.adsadvance.ui.main.MainActivity

class SigninFragment : BaseBindingFragment(), SigninContract.View {

        private lateinit var binding : FragmentSigninBinding
        private lateinit var presenter: SigninPresenter

        override fun getFragmentView(): ViewBinding {
            binding = FragmentSigninBinding.inflate(layoutInflater)
            return binding
        }

        override fun onBindView() {
            presenter = SigninPresenter(this)

            binding.btnSigin.setOnClickListener {

                val emailParms = binding.etEmail.text.toString()
                val passParms = binding.etPass.text.toString()

                if (emailParms.isNullOrEmpty()) {
                    showSnackbarMessage(binding.btnSigin, "Silahkan isi email", Const.ToastType.Error)
                } else if (passParms.isNullOrEmpty()) {
                    showSnackbarMessage(binding.btnSigin, "Silahkan isi pass", Const.ToastType.Error)
                } else {
                    presenter.setSignin(
                        emailParms,
                        passParms
                    )
                }

            }
        }

        override fun onSigninSuccess(loginResponse: LoginResponse) {

            Log.v("tamvan", "ini data 1 "+ loginResponse.key)

            BagicodeTravel.getApp().setToken(loginResponse.key!!)

            Log.v("tamvan", "ini data 2 "+BagicodeTravel.getApp().getToken())

            val gson = Gson()
            val json = gson.toJson(loginResponse)

            BagicodeTravel.getApp().setUser(json)

            Log.v("tamvan", "ini data 3 "+BagicodeTravel.getApp().getUser())

            changePage(MainActivity::class.java, null, activity, true)
        }

        override fun onSigninFailed(message: String) {
            showSnackbarMessage(binding.btnSigin, message, Const.ToastType.Error)
        }
}