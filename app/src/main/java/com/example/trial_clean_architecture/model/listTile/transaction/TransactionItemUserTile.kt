package com.example.trial_clean_architecture.model.listTile.transaction

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.R
import com.example.trial_clean_architecture.databinding.ItemTransactionWithUserBinding
import com.example.trial_clean_architecture.model.Transactions
import com.example.trial_clean_architecture.model.enums.TransactionViewType
import com.example.trial_clean_architecture.ui.transaction.adapter.TransactionUserItemViewHolder
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.ui.common.Tile

object TransactionItemUserTile : Tile<ListItem>() {
    override fun isTypeOf(item: ListItem?): Boolean {
        var isUserTile = false
        if(item is Transactions){
            item.transaction?.let {
                isUserTile = it.viewType != TransactionViewType.CashIn.value && it.viewType != TransactionViewType.CashOut.value
            }
        }
        return isUserTile
    }

    override fun type(): Int {
        return R.layout.item_transaction_with_user
    }

    override fun holder(
        parent: ViewGroup,
        context: Context,
        updateAdapter: () -> Unit
    ): RecyclerView.ViewHolder {
        return TransactionUserItemViewHolder(parent.viewOf(type()) as ItemTransactionWithUserBinding,context )
    }

    override fun bind(holder: RecyclerView.ViewHolder, item: ListItem?) {
        if (holder is TransactionUserItemViewHolder && item is Transactions) {
            holder.bind(item)
        }
    }
}