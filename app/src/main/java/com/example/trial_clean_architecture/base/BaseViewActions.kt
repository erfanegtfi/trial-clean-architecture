package com.example.trial_clean_architecture.base;

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.example.common.utils.GeneralError
import com.example.common.utils.response.ApiBaseResponse


class BaseViewActions private constructor() {

    private var progress: Dialog? = null
    private lateinit var context: Context

    companion object {
        fun getInstance(context: Context) =  BaseViewActions().also {
            it.context = context
        }
    }

//    public static BaseViewActions getInstance() {
//        return new BaseViewActions();
//    }

    fun showLoading(message: String?) {
    }

    public fun showLoading() {
//          Dialog(context, R.style.CustomLoadingDialogTheme).apply {
//            progress = this
//              window?.setBackgroundDrawableResource(android.R.color.transparent)
//              window?.requestFeature(Window.FEATURE_NO_TITLE)
//              setContentView(ProgressBar(context))
//              if (!isShowing) show()
//        }
    }

    public fun hideLoading() {
        progress?.let {
            if (it.isShowing) it.dismiss()
        }

    }

    public fun unauthorizedUser(response: ApiBaseResponse?) {
//        if (response.httpCode == 401) {
//            UtilsMessage.showLovelyStandardDialog(
//                context,
//                R.string.auth_dialog_login_required_login,
//                R.string.auth_dialog_login_required_register,
//                R.string.auth_dialog_login_required_close,
//                R.string.auth_dialog_login_required_error,
//                R.string.auth_dialog_login_required_title,
//                R.drawable.ic_nav_user,
//                (dialogInterface,
//                i
//            ) -> AuthenticationActivity.start(context, AuthenticationActivity.OPEN_TYPE_LOGIN)
//            , (dialogInterface, i) -> AuthenticationActivity.start(context, AuthenticationActivity.OPEN_TYPE_REGISTER)
//            , (dialogInterface, i) -> {
//
//            });
//        } else { // on wrong verification code
//            onResponseMessage(response, context);
//        }
    }


    fun onTimeout(throwable: Throwable?) {

    }

    fun onNetworkError(throwable: Throwable?) {
        Toast.makeText(context, "خطا در اتصال به اینترنت!", Toast.LENGTH_SHORT).show();
    }

    fun onError(error: GeneralError?) {

    }

    fun onResponseMessage(message: ApiBaseResponse?) {
//        if (!message.msg.isNullOrEmpty()) //&& !message.isSuccess()
//            if (message.httpCode == Constants.CODE_SUCCESS) {
//                if (message.messageShowType == MessageShowType.DIALOG) UtilsMessage.showDialog(context, "تایید", "", message.msg);
//                else //if (message.getMessageShowType() == MessageShowType.TOAST)
//                    Toast.makeText(context, message.getMsg(), Toast.LENGTH_SHORT).show();
//            } else UtilsMessage.showDialog(context, "تایید", "", message.getMsg());
    }


}
