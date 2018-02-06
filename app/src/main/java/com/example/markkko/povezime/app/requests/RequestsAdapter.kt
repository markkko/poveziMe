package com.example.markkko.povezime.app.requests

import android.content.Context
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.adapters.BaseViewHolder
import com.example.markkko.povezime.app.base.views.adapters.RecyclerItemAdapter
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.models.AnswerType
import com.example.markkko.povezime.core.models.MatchInfo
import com.example.markkko.povezime.core.models.Ride
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

        @BindView(R.id.myDate) lateinit var myDate: TextView

        @BindView(R.id.userDate) lateinit var userDate: TextView

        override fun fill(entity: Any, context: Context) {
            super.fill(entity, context)

            val ride = entity as MatchInfo

            val me: Ride
            val other: Ride
            if (ride.type == RideType.O) {
                me = ride.offer
                other = ride.search
            } else{
                me = ride.search
                other = ride.offer
            }

            from.text = context.getString(R.string.ride_from, me.fromName)
            to.text = context.getString(R.string.ride_to, me.toName)
            myDate.text = me.date
            userName.text = other.user!!.name
            userRoute.text = context.getString(R.string.from_to, other.fromName, other.toName)
            userDate.text = other.date

            itemView.setBackgroundResource(when (ride.answer) {
                AnswerType.A -> R.drawable.empty_accept
                AnswerType.D -> R.drawable.empty_declined
                AnswerType.PENDING -> R.drawable.empty_pending
            })
        }
    }
}