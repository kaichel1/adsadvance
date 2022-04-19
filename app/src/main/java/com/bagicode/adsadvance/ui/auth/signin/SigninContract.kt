package com.bagicode.adsadvance.ui.auth.signin

import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.bagicode.adsadvance.model.Response.LoginResponse

interface SigninContract {
    interface View : BaseView {
        fun onSigninSuccess(loginResponse: LoginResponse)
        fun onSigninFailed(message : String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun setSignin (email:String, pass:String)
    }
}