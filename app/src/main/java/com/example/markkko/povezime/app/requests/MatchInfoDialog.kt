package com.example.markkko.povezime.app.requests


import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.markkko.povezime.R
import com.example.markkko.povezime.core.models.AnswerType
import com.example.markkko.povezime.core.models.MatchInfo
import com.example.markkko.povezime.core.models.Ride
import com.example.markkko.povezime.core.models.RideType


class MatchInfoDialog : DialogFragment() {

    /*********** Fields **********/

    private lateinit var match: MatchInfo

    @BindView(R.id.fromTo) lateinit var fromTo: TextView
    @BindView(R.id.date) lateinit var date: TextView
    @BindView(R.id.userName) lateinit var userName: TextView
    @BindView(R.id.userRoute) lateinit var userRoute: TextView
    @BindView(R.id.userDate) lateinit var userDate: TextView
    @BindView(R.id.userTime) lateinit var userTime: TextView
    @BindView(R.id.userCar) lateinit var userCar: TextView
    @BindView(R.id.userPhone) lateinit var userPhone: TextView
    @BindView(R.id.viber) lateinit var viber: ImageView
    @BindView(R.id.whatsapp) lateinit var whatsapp: ImageView
    @BindView(R.id.acceptDecline) lateinit var acceptDecline: View
    @BindView(R.id.delete) lateinit var delete: View

    lateinit var listener: Listener

    /*********** LifeCycle **********/

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity, R.style.FullscreenTheme)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(activity.resources.getDrawable(R.drawable.black_transparent))
        val view = View.inflate(activity, R.layout.dialog_match_info, null)
        dialog.setContentView(view)
        ButterKnife.bind(this, view)
        setupUI()

        return dialog
    }

    /*********** Callbacks **********/

    @OnClick(R.id.decline, R.id.accept, R.id.delete, R.id.back)
    fun onClickButton(view: View) {
        when (view.id) {
            R.id.decline -> listener.onClickDecline(match)
            R.id.accept -> listener.onClickAccept(match)
            R.id.delete -> listener.onClickDelete(match)
            R.id.back -> dismiss()
        }
    }

    /*********** Internal **********/

    private fun setupUI() {
        userCar.visibility = View.INVISIBLE

        val myRide: Ride
        val userRide: Ride

        if (match.type == RideType.O) {
            myRide = match.offer
            userRide = match.search
        } else {
            myRide = match.search
            userRide = match.offer
        }

        userRide.user?.let {
            viber.setImageResource(if (it.viber > 0) R.drawable.ic_viber_active
            else R.drawable.ic_viber_passive)
            whatsapp.setImageResource(if (it.whatsapp > 0) R.drawable.ic_whatsapp_active
            else R.drawable.ic_whatsapp_passive)
        }

        fromTo.text = getString(R.string.from_to, myRide.fromName, myRide.toName)
        date.text = myRide.date

        userName.text = getString(R.string.full_name, userRide.user!!.name, userRide.user!!.surname)
        userRoute.text = getString(R.string.from_to, userRide.fromName, userRide.toName)
        userDate.text = userRide.date
        userTime.visibility = View.GONE
        userPhone.text = userRide.user!!.phone

        if (match.answer == AnswerType.PENDING) {
            acceptDecline.visibility = View.VISIBLE
            delete.visibility = View.GONE
        } else {
            acceptDecline.visibility = View.GONE
            delete.visibility = View.VISIBLE
        }

    }

    private fun setupUForOffer(myRide: Ride, userRide: Ride) {}

    private fun setupForSearch() {}

    /*********** Listener **********/

    interface Listener {
        fun onClickDecline(match: MatchInfo)
        fun onClickAccept(match: MatchInfo)
        fun onClickDelete(match: MatchInfo)
    }

    companion object {

        fun show(fragmentManager: FragmentManager, match: MatchInfo, listener: Listener): MatchInfoDialog {
            val dialog = MatchInfoDialog()
            dialog.match = match
            dialog.listener = listener
            dialog.show(fragmentManager, "dialog_allergen")
            return dialog
        }
    }
}
