package com.example.trial_clean_architecture.base.view;


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.utils.ErrorType
import com.example.common.utils.GeneralError
import com.example.common.utils.ResponseResult
import com.example.common.utils.response.ApiBaseResponse
import com.example.trial_clean_architecture.base.BaseView
import com.example.trial_clean_architecture.base.BaseViewModel
import com.example.trial_clean_architecture.base.BaseViewActions
import com.example.trial_clean_architecture.di.viewModel.AppViewModelFactory
import com.example.trial_clean_architecture.utils.extentions.safeNullCheck
import javax.inject.Inject

/**
 * All Fragments must extends from this Base class
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment(), BaseView {
    private lateinit var viewActions: BaseViewActions


    protected lateinit var binding: VDB
    protected lateinit var viewModel: VM

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val viewModelClass: Class<VM>
    protected abstract val bindingVariable: Int?

    @Inject
    lateinit var  viewModelFactory: AppViewModelFactory


//    protected lateinit var baseActivity: BaseActivity<*, *>
//    protected lateinit var contextFragment: Context

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is BaseActivity<*, *>) {
//            this.baseActivity = context
//        }
//        this.contextFragment = context
//    }

    protected fun bindView(inflater: LayoutInflater, container: ViewGroup?): VDB = DataBindingUtil.inflate<VDB>(inflater, layoutRes, container, false).apply {
        binding = this
        binding.lifecycleOwner = this@BaseFragment

        setBindingVariable()
        return binding
    }

    private fun setBindingVariable(){
        safeNullCheck(bindingVariable, viewModel) { variable, vm ->
            binding.setVariable(variable, vm)
            binding.executePendingBindings()
        }
    }

    protected fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(layoutRes, container, false)
    }

    private fun obtainViewModel() = ViewModelProvider(this, viewModelFactory).get(viewModelClass).apply {
        viewModel = this
        subscribeLoadingListener()
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewActions = BaseViewActions.getInstance(requireContext())
        obtainViewModel()
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        bind(inflater, container)
//        return binding.root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        bindingVariable?.let {
//            binding.setVariable(it, viewModel)
//            binding.executePendingBindings()
//        }
//    }

    protected fun finishActivity() {
//        baseActivity.finish()
    }

    private fun subscribeLoadingListener() {
        viewModel.apiEvents.observe(this, Observer {
            when (it) {
                is ResponseResult.Loading -> {
                    showLoading()
                }
                is ResponseResult.Success -> {
                    hideLoading()
                    onResponseMessage(it.response)
                }
                is ResponseResult.Error -> { // on errors
//                    hideLoading()
                    parsError(it.error)
                }
            }
        })
    }

    private fun parsError(callEvent: GeneralError) {
        hideLoading()
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
    ///////////////////////

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
        viewActions.onError(error);
    }

    override fun onResponseMessage(message: ApiBaseResponse?) {
        viewActions.onResponseMessage(message);
    }



    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }




    open fun onBackPressed(): Boolean {
        return true;
    }
}
