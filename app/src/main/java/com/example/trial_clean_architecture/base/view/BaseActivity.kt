package com.example.trial_clean_architecture.base.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.LayoutRes


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.example.common.utils.ErrorType
import com.example.common.utils.GeneralError
import com.example.common.utils.ResponseResult
import com.example.common.utils.response.ApiBaseResponse
import com.example.trial_clean_architecture.base.BaseView
import com.example.trial_clean_architecture.base.BaseViewModel
import com.example.trial_clean_architecture.base.BaseViewActions
import com.example.trial_clean_architecture.di.viewModel.AppViewModelFactory
import javax.inject.Inject
import com.example.trial_clean_architecture.utils.extentions.safeNullCheck

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), BaseView {

    var isFinished: Boolean? = null
    var activity: BaseActivity<*, *>? = null
    var isContentLoaded: Boolean? = null
    var progress: Dialog? = null

    lateinit var viewActions: BaseViewActions;

    protected lateinit var binding: VDB
    protected lateinit var viewModel: VM

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val viewModelClass: Class<VM>
    protected abstract val bindingVariable: Int?

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    protected fun bindView(): VDB = DataBindingUtil.setContentView<VDB>(this, layoutRes).apply {
        lifecycleOwner = this@BaseActivity
        binding = this

        setBindingVariable()
        return binding
    }

    private fun setBindingVariable(){
        safeNullCheck(bindingVariable, viewModel) { variable, vm ->
            binding.setVariable(variable, vm)
            binding.executePendingBindings()
        }
    }

    private fun obtainViewModel() = ViewModelProvider(this, viewModelFactory).get(viewModelClass).apply {
        viewModel = this
        subscribeLoadingListener()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        obtainViewModel()
        bindView()
        viewActions = BaseViewActions.getInstance(this)
        activity = this
    }


    private fun subscribeLoadingListener() {
        viewModel.apiEvents.observe(this, Observer {
            when (it) {
                is ResponseResult.Loading -> {
                    showLoading();
                }
                is ResponseResult.Success -> {
                    hideLoading();
                    onResponseMessage(it.response)
                }
                is ResponseResult.Error -> { // on errors
                    hideLoading();
                    parsError(it.error)
                }
            }
        })
    }

    private fun parsError(callEvent: GeneralError) {
        when (callEvent.errorType) {
            is ErrorType.ResponseError -> {
                onResponseMessage(callEvent.response)
            }
            is ErrorType.UnAuthorizedError -> {
                unauthorizedUser(callEvent.response)
            }
            is ErrorType.NetworkError -> {
                onNetworkError(callEvent.throwable)
            }
            is ErrorType.TimeOutError -> {
                onTimeout(callEvent.throwable)
            }
            else -> {}
        }
    }


    fun internetConnection() {
        Toast.makeText(activity, "connection error!", Toast.LENGTH_SHORT).show();
    }


    override fun showLoading(message: String?) {
        viewActions.showLoading(message)
    }

    override fun showLoading() {
        viewActions.showLoading()
    }

    override fun hideLoading() {
        viewActions.hideLoading()
    }

    override fun unauthorizedUser(response: ApiBaseResponse?) {
        viewActions.unauthorizedUser(response);
    }

    override fun onTimeout(throwable: Throwable?) {
        viewActions.onTimeout(throwable);
    }

    override fun onNetworkError(throwable: Throwable?) {
        viewActions.onNetworkError(throwable);
    }

    override fun onError(error: GeneralError?) {
        viewActions.onError(error)
    }

    override fun onResponseMessage(message: ApiBaseResponse?) {
        viewActions.onResponseMessage(message)
    }


    override fun onDestroy() {
        isFinished = true
        viewModel.onDestroy()
//        binding.unbind()
        super.onDestroy()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    fun getAppComponent(): AppComponent {
//        return (application as App).getAppComponent()
//    }

    override fun onBackPressed() {
        super.onBackPressed();
    }

}
