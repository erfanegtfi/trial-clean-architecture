package com.example.trial_clean_architecture.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import com.example.trial_clean_architecture.App
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.base.BaseViewModel
import com.example.trial_clean_architecture.base.view.BaseFragment
import com.example.trial_clean_architecture.ui.transaction.di.DaggerTransactionComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class ContactFragment : BaseFragment<ViewDataBinding, BaseViewModel>() {

    override val layoutRes: Int = R.layout.fragment_contact
    override val viewModelClass: Class<BaseViewModel> = BaseViewModel::class.java
    override val bindingVariable: Int? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        DaggerTransactionComponent.factory().create((requireActivity().application as App).getAppComponent()).inject(this)
        super.onCreate(savedInstanceState);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflateView(inflater, container)
        return rootView
    }

}
