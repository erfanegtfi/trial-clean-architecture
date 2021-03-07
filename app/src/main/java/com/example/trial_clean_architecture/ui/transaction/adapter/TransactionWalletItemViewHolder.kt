package com.example.trial_clean_architecture.ui.transaction.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.databinding.ItemTransactionWithWalletBinding
import com.example.trial_clean_architecture.model.Transactions
import com.example.trial_clean_architecture.model.enums.TransactionViewType.Companion.transientViewType

class TransactionWalletItemViewHolder(private val binding: ItemTransactionWithWalletBinding, private val context: Context) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(model: Transactions) {
        binding.transactions = model
        binding.txtReceivedFrom.text = transientViewType(model.transaction?.viewType, context, model.userFullName, model.transaction?.status)
    }
}