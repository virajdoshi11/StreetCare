package org.brightmindenrichment.street_care.ui.user

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException


open interface GoogleSignInListener {
    fun onSignInSuccess()
    fun onSignInError()
}