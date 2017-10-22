package com.gazindo.android.android.base.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val view: View = itemView


    internal var entity: Any? = null

    open fun setupListener(listener: RecyclerItemAdapter.ItemClickListener?) {
            itemView.setOnClickListener(View.OnClickListener {
                validateView ->
                entity?.let { listener?.onItemClick(null, validateView, adapterPosition, it) }
            })
    }


    fun setupWatcher(watcher: RecyclerItemAdapter.TextWatchListener) {}

    open fun fill(entity: Any, context: Context) {
        this.entity = entity
    }
}
