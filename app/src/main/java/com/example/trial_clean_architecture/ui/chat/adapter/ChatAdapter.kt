package com.example.trial_clean_architecture.ui.chat.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trial_clean_architecture.ui.common.ListItem
import com.example.trial_clean_architecture.ui.common.Tile
import com.example.trial_clean_architecture.ui.common.TileTypes

 class ChatAdapter(
    vararg types: Tile<ListItem>,
    private val items: List<ListItem>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val tileTypes: TileTypes<ListItem> = TileTypes(*types)

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return tileTypes.of(item).type()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return tileTypes.of(viewType).holder(parent, context, ::updateAdapter)
    }

     private fun updateAdapter() {

     }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        tileTypes.of(item).bind(holder, item)
    }

     override fun getItemCount(): Int {
         return items.size
     }
 }
