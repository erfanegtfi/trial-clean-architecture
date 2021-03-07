package com.example.trial_clean_architecture.ui.transaction.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.ui.common.BASE_DIFF_CALLBACK
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.ui.common.Tile
import com.example.trial_clean_architecture.ui.common.TileTypes

class TransactionAdapter(
    vararg types: Tile<ListItem>,
    private val context: Context
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {

    private val tileTypes: TileTypes<ListItem> = TileTypes(*types)

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return tileTypes.of(item).type()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return tileTypes.of(viewType).holder(parent, context, ::updateAdapter)
    }

    private fun updateAdapter() {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        tileTypes.of(item).bind(holder, item)
    }
}
