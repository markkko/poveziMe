package com.example.markkko.povezime.app.results.search

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.adapters.BaseViewHolder
import com.example.markkko.povezime.app.base.views.adapters.RecyclerItemAdapter
import com.example.markkko.povezime.core.models.Offer
import javax.inject.Inject


class OffersAdapter @Inject constructor(context: Context)
    : RecyclerItemAdapter<Offer, OffersAdapter.ViewHolder>(layoutId = R.layout.item_search_result, context = context) {

    override fun createHolder(view: View): ViewHolder = ViewHolder(view)

    fun updateRequestSent(id: Long) {
        val result = items.first { it.id == id }
        result.isSent = true
    }

    inner class ViewHolder(view: View) : BaseViewHolder(view) {

        @BindView(R.id.name) lateinit var name: TextView

        @BindView(R.id.route) lateinit var route: TextView

        @BindView(R.id.phone) lateinit var phone: TextView

        @BindView(R.id.request) lateinit var request: Button

        override fun setupListener(listener: ItemClickListener?) {
            listener?.let {
                request.setOnClickListener({ validateView ->
                    it.onItemClick(null, validateView, adapterPosition, entity)
                })
            }
        }

        override fun fill(entity: Any, context: Context) {
            super.fill(entity, context)

            val result = entity as Offer

            name.text = result.user
            request.setBackgroundColor(if (result.isSent) context.resources.getColor(R.color.gray_5)
            else context.resources.getColor(R.color.colorAccent))

        }
    }
}