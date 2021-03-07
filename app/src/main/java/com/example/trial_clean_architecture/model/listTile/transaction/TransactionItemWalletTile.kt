package com.example.trial_clean_architecture.model.listTile.transaction

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.databinding.ItemTransactionWithWalletBinding
import com.example.trial_clean_architecture.model.Transactions
import com.example.trial_clean_architecture.model.enums.TransactionViewType
import com.example.trial_clean_architecture.ui.transaction.adapter.TransactionWalletItemViewHolder
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.ui.common.Tile

object TransactionItemWalletTile : Tile<ListItem>() {
    override fun isTypeOf(item: ListItem?): Boolean {
        var isWalletTile = false
        if(item is Transactions){
            item.transaction?.let {
                isWalletTile = it.viewType == TransactionViewType.CashIn.value || it.viewType == TransactionViewType.CashOut.value
            }
        }

        return isWalletTile
    }

    override fun type(): Int {
        return R.layout.item_transaction_with_wallet
    }

    override fun holder(
        parent: ViewGroup,
        context: Context,
        updateAdapter: () -> Unit
    ): RecyclerView.ViewHolder {
        return TransactionWalletItemViewHolder(parent.viewOf(type()) as ItemTransactionWithWalletBinding, context)
    }

    override fun bind(holder: RecyclerView.ViewHolder, item: ListItem?) {
        if (holder is TransactionWalletItemViewHolder && item is Transactions) {
            holder.bind(item)
        }
    }
}