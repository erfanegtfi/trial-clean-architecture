package com.example.trial_clean_architecture.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class Tile<T> {
    abstract fun isTypeOf(item: T?): Boolean
    abstract fun type(): Int
    abstract fun holder(parent: ViewGroup, context: Context, updateAdapter: () -> Unit): RecyclerView.ViewHolder
    abstract fun bind(holder: RecyclerView.ViewHolder, item: T?)

    protected fun ViewGroup.viewOf(@LayoutRes layout: Int): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(context), layout, this, false)
//        return LayoutInflater.from(context).inflate(layout, this, false)
    }
}