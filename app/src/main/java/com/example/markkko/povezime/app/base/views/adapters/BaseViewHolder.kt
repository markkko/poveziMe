package com.example.markkko.povezime.app.base.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val view: View = itemView

    lateinit var entity: Any

    open fun setupListener(listener: RecyclerItemAdapter.ItemClickListener?) {
        listener?.let {
            itemView.setOnClickListener({ validateView ->
                it.onItemClick(null, validateView, adapterPosition, entity)
            })
        }
    }


    fun setupWatcher(watcher: RecyclerItemAdapter.TextWatchListener) {}

    open fun fill(entity: Any, context: Context) {
        this.entity = entity
    }
}
