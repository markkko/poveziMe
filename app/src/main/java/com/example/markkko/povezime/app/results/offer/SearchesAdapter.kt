package com.example.markkko.povezime.app.results.offer

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.adapters.BaseViewHolder
import com.example.markkko.povezime.app.base.views.adapters.RecyclerItemAdapter
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.util.convertDateFromDatabase
import com.example.markkko.povezime.core.util.convertTimeFromDatabase
import javax.inject.Inject
import javax.inject.Named


class SearchesAdapter @Inject constructor(@Named(AppConstants.ACTIVITY_CONTEXT) context: Context)
    : RecyclerItemAdapter<Search, SearchesAdapter.ViewHolder>(layoutId = R.layout.item_search_result, context = context) {

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
        @BindView(R.id.viber) lateinit var viber: ImageView
        @BindView(R.id.whatsapp) lateinit var whatsapp: ImageView
        @BindView(R.id.date) lateinit var date: TextView

        override fun setupListener(listener: ItemClickListener?) {
            listener?.let {
                request.setOnClickListener({ validateView ->
                    it.onItemClick(null, validateView, adapterPosition, entity)
                })
            }
        }

        override fun fill(entity: Any, context: Context) {
            super.fill(entity, context)

            val result = entity as Search
            val user = result.user

            name.text = context.getString(R.string.full_name, user!!.name, user.surname)
            route.text = context.getString(R.string.from_to, result.fromName, result.toName)
            phone.text = user.phone
            viber.setImageResource(if (user.viber > 0) R.drawable.ic_viber_active
                else R.drawable.ic_viber_passive)
            whatsapp.setImageResource(if (user.whatsapp > 0) R.drawable.ic_whatsapp_active
                else R.drawable.ic_whatsapp_passive)
            request.text = if (result.isSent) context.getString(R.string.requests_request_sent) else context.getString(R.string.requests_send_request)
            request.setBackgroundResource(if (result.isSent) R.drawable.round_gray_s
                else R.drawable.round_accent_s)
            date.text = convertDateFromDatabase(result.date)
        }
    }
}