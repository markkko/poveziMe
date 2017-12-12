package com.example.markkko.povezime.app.profile

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import butterknife.OnItemSelected
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.app.base.views.BaseFragmentedActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.util.getStringSafe
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.app.util.isValidEmail
import com.example.markkko.povezime.app.util.isValidPhoneNumber
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.profile.IProfileMVP
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileActivity : BaseFragmentedActivity() {

    override fun getFragment(): Fragment =
            ProfileFragment.newInstance()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    class ProfileFragment : BaseFragment(), IProfileMVP.View {

        /*********** Fields **************/

        override val layoutId: Int = R.layout.fragment_profile

        override val hasOptionMenu: Boolean = true
        @Inject
        lateinit var presenter: IProfileMVP.Presenter

        private var isInEditingState: Boolean = false

        private var selectedCar: Car? = null

        var save: MenuItem? = null
        var edit: MenuItem? = null

        /*********** Callbacks **************/

        override fun showMessage(message: String) {}

        override fun showOfflineMessage(isCritical: Boolean) {}

        override fun onUserUpdated(user: User) {
            baseActivity.showToast(getString(R.string.profile_user_updated))
            isInEditingState = false
            refreshUI()
        }

        /*********** Setup **************/

        override fun bind() {
            presenter.view = this
        }

        override fun injectDependencies(application: PoveziMeApplication) {
            application.activityComponent().inject(this)
        }

        override fun load() {
            refreshUI()
        }

        @OnItemSelected(R.id.carSpinner)
        fun onSelectedCarChanged(position: Int) {
            selectedCar = presenter.me().cars[position]
        }

        /*********** Internal **************/

        private fun refreshUI() {
            val user = presenter.me()

            if (!isInEditingState) {
                name.isEnabled = false
                surname.isEnabled = false
                phone.isEnabled = false
                email.isEnabled = false
            } else {
                name.isEnabled = true
                surname.isEnabled = true
                phone.isEnabled = true
                email.isEnabled = true
            }

            name.setText(user.name)
            surname.setText(user.surname)
            phone.setText(user.phone)
            email.setText(user.email)

            val spinnerValues = java.util.ArrayList<String>()
            user.cars.forEach { spinnerValues.add(it.make + " " + it.model) }

            val spinnerAdapter = ArrayAdapter(activity, R.layout.spinner_item_simple, spinnerValues)
            spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            carSpinner.adapter = spinnerAdapter
        }

        /*********** Action Bar **************/

        override fun onPrepareOptionsMenu(menu: Menu?) {
            menu?.let {
                save = it.findItem(R.id.action_save)
                edit = it.findItem(R.id.action_edit)
            }
            save?.isVisible = false
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.action_edit -> {
                    isInEditingState = true
                    refreshUI()
                }
                R.id.action_save -> {
                    var valid = true
                    if (isNullOrEmpty(name)) {
                        valid = false
                        name.error = getString(R.string.empty_name)
                    }
                    if (isNullOrEmpty(surname)) {
                        valid = false
                        surname.error = getString(R.string.empty_surname)
                    }
                    if (!isValidPhoneNumber(phone.text)) {
                        valid = false
                        phone.error = getString(R.string.empty_phone)
                    }
                    if (!isValidEmail(email)) {
                        valid = false
                        email.error = getString(R.string.empty_email)
                    }
                    if (valid) presenter.updateUser(createUser())
                }
            }
            return super.onOptionsItemSelected(item)
        }

        private fun createUser(): User {
            val user = presenter.me().makeDeepCopy()
            user.name = getStringSafe(name)
            user.surname = getStringSafe(surname)
            user.phone = getStringSafe(phone)
            user.email = getStringSafe(email)
            selectedCar?.let { user.selectedCar = it }
            return user
        }

        /*********** Companion **************/

        companion object {
            fun newInstance() = ProfileFragment()
        }
    }
}
