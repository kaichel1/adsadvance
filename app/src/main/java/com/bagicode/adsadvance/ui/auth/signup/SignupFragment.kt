package com.bagicode.adsadvance.ui.auth.signup

import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.adsadvance.databinding.FragmentSignupBinding

class SignupFragment : BaseBindingFragment(), SignupContract.View {

    lateinit var binding : FragmentSignupBinding
    lateinit var presenter: SignupPresenter

    override fun getFragmentView(): ViewBinding {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = SignupPresenter(this)

        binding.btnRegister.setOnClickListener {
            val usernameParms = binding.etUsernanme.text.toString()
            val emailParms = binding.etEmail.text.toString()
            val passParms = binding.etPass.text.toString()

            if (usernameParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnRegister, "Silahkan isi username", Const.ToastType.Error)
            } else if (emailParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnRegister, "Silahkan isi email", Const.ToastType.Error)
            } else if (passParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnRegister, "Silahkan isi pass", Const.ToastType.Error)
            } else {
                presenter.setSignup(
                    emailParms,
                    passParms,
                    usernameParms
                )
            }
        }
    }

    override fun onSignupSuccess(message: String) {
        showSnackbarMessage(binding.btnRegister, message, Const.ToastType.Success)

        binding.etUsernanme.setText("")
        binding.etEmail.setText("")
        binding.etPass.setText("")
    }

    override fun onSignupFailed(message: String) {
        showSnackbarMessage(binding.btnRegister, message, Const.ToastType.Error)
    }
}