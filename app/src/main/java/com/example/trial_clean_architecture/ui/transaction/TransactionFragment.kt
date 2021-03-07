package com.example.trial_clean_architecture.ui.transaction

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.ResponseResult
import com.example.trial_clean_architecture.BR
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.base.view.BaseFragment
import com.example.trial_clean_architecture.databinding.FragmentTransactionListBinding
import com.example.trial_clean_architecture.model.listTile.transaction.TransactionItemWalletTile
import com.example.trial_clean_architecture.model.listTile.transaction.TransactionItemUserTile
import com.example.trial_clean_architecture.ui.transaction.adapter.TransactionAdapter
import com.example.trial_clean_architecture.ui.transaction.di.DaggerTransactionComponent
import com.example.trial_clean_architecture.utils.extentions.findAppComponent
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.fragment_transaction_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.math.abs

@ExperimentalCoroutinesApi
class TransactionFragment : BaseFragment<FragmentTransactionListBinding, TransactionViewModel>(), OnOffsetChangedListener,
    View.OnClickListener {

    private var isSearching: Boolean = false
    override val layoutRes: Int = R.layout.fragment_transaction_list
    override val viewModelClass: Class<TransactionViewModel> = TransactionViewModel::class.java
    override val bindingVariable: Int = BR.viewModel

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        DaggerTransactionComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState);

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindView(inflater, container)
        return binding.root
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionAdapter(TransactionItemWalletTile, TransactionItemUserTile, context = requireContext())

        recycleView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recycleView.adapter = adapter

        viewModel.transactionListLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResponseResult.Success -> {
                    adapter.submitList(it.response)
                }
                is ResponseResult.Error -> {
                    adapter.submitList(null)
                }
                else -> {
                }
            }

        })

//        viewModel.searchPostListLiveData.observe(viewLifecycleOwner, Observer {
//            when (it) {
//                is ResponseResult.Success -> {
//
//                    adapter.submitList(it.response)
//                }
//                else -> {
//                }
//            }
////            adapter.submitList(it)
//
//        })

        viewModel.getTransactionList()

        binding.appbar.addOnOffsetChangedListener(this)
        binding.imgSearch.setOnClickListener(this)

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isSearching = true
                viewModel.setSearchQuery(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    var maxDist = 0
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (maxDist == 0)
            maxDist = binding.appbar.height - binding.toolbar.height
        getAlphaForToolbar(verticalOffset, maxDist)
    }


    private fun getAlphaForToolbar(scrollY: Int, maxDist: Int) {
        when {
            abs(scrollY) >= maxDist -> {
                binding.toolbar.alpha = 1F
            }
            scrollY < 0 -> {
                binding.toolbar.alpha = abs((1.0 / maxDist.toDouble() * scrollY.toDouble()).toFloat())
            }
            else -> {
                binding.toolbar.alpha = 0F
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgSearch -> {
                if (validInput()) {
                    val searchQuery = binding.edtSearch.text.toString()
                    viewModel.setSearchQuery(searchQuery)
                }
            }
        }
    }

    fun validInput(): Boolean {
        var isValid = true
        val searchQuery = binding.edtSearch.text
        if (searchQuery.isBlank())
            isValid = false

        return isValid
    }
}
