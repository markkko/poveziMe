package com.example.markkko.povezime.app.requests

import android.content.Context
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.adapters.BaseViewHolder
import com.example.markkko.povezime.app.base.views.adapters.RecyclerItemAdapter
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.models.MatchInfo
import com.example.markkko.povezime.core.models.RideType
import javax.inject.Inject
import javax.inject.Named


class RequestsAdapter @Inject constructor(@Named(AppConstants.ACTIVITY_CONTEXT) context: Context)
    : RecyclerItemAdapter<MatchInfo, RequestsAdapter.ViewHolder>(layoutId = R.layout.item_request, context = context) {

    override fun createHolder(view: View): ViewHolder =
            ViewHolder(view)

    inner class ViewHolder(view: View): BaseViewHolder(view) {

        @BindView(R.id.from) lateinit var from: TextView

        @BindView(R.id.to) lateinit var to: TextView

        @BindView(R.id.userName) lateinit var userName: TextView

        @BindView(R.id.userRoute) lateinit var userRoute: TextView

        override fun fill(entity: Any, context: Context) {
            super.fill(entity, context)

            val ride = entity as MatchInfo

            if (ride.type == RideType.O) setupOffer(ride, context)
            else setupSearch(ride, context)

        }

        private fun setupSearch(match: MatchInfo, context: Context) {
            from.text = context.getString(R.string.ride_from, match.search.fromName)
            to.text = context.getString(R.string.ride_to, match.search.toName)
            userName.text = match.offer.user!!.name
            userRoute.text = context.getString(R.string.from_to, match.offer.fromName, match.offer.toName)
        }

        private fun setupOffer(match: MatchInfo, context: Context) {
            from.text = context.getString(R.string.ride_from, match.offer.fromName)
            to.text = context.getString(R.string.ride_to, match.offer.toName)
            userName.text = match.search.user!!.name
            userRoute.text = context.getString(R.string.from_to, match.search.fromName, match.search.toName)
        }
    }

}