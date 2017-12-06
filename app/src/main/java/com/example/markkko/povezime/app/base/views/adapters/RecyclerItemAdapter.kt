package com.example.markkko.povezime.app.base.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife


abstract class RecyclerItemAdapter<T : Any, E : BaseViewHolder>(items: List<T> = ArrayList(), private val layoutId: Int, private val context: Context) : RecyclerView.Adapter<E>() {

    /*********************************
     * Fields
     **********************************/

    var items: List<T> = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: ItemClickListener? = null

    var watcher: TextWatchListener? = null

    /*********************************
     * Public API
     **********************************/

    private fun getItem(position: Int): T? {
        return if (position in 0..(itemCount - 1)) {
            this.items[position]
        } else null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): E {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(layoutId, parent, false)
        val holder = createHolder(view)
        ButterKnife.bind(holder, view)
        holder.setupListener(listener)
        watcher?.let { holder.setupWatcher(it) }
        return holder
    }

    override fun onBindViewHolder(holder: E, position: Int) {
        holder.fill(getItem(position)!!, context)
    }


    override fun getItemCount(): Int {
        return this.items.size
    }

    /*********************************
     * Abstract
     **********************************/

    protected abstract fun createHolder(view: View): E

    /*********************************
     * Listener
     **********************************/

    interface ItemClickListener {
        fun onItemClick(parent: View?, v: View, position: Int, entity: Any)
    }

    interface TextWatchListener {
        fun beforeTextChanged(parent: View, s: CharSequence, start: Int, count: Int, after: Int, position: Int, entity: Any)
        fun onTextChanged(parent: View, s: CharSequence, start: Int, before: Int, count: Int, position: Int, entity: Any)
        fun afterTextChanged(parent: View, s: Editable, position: Int, entity: Any)
    }

}

