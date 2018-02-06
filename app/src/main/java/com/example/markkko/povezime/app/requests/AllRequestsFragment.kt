package com.example.markkko.povezime.app.requests

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.app.base.views.adapters.RecyclerItemAdapter
import com.example.markkko.povezime.core.models.MatchInfo
import com.example.markkko.povezime.core.requests.IRequestsMVP
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject


class AllRequestsFragment : BaseFragment(), IRequestsMVP.View, MatchInfoDialog.Listener {

    override val layoutId: Int = R.layout.fragment_requests

    /********* Fields *********/

    @Inject
    lateinit var presenter: IRequestsMVP.Presenter

    @Inject
    lateinit var adapter: RequestsAdapter

    /********* Callbacks *********/

    override fun showMessage(message: String) {}

    override fun showOfflineMessage(isCritical: Boolean) {}

    override fun onRequestsFetched(requests: List<MatchInfo>) { adapter.items = requests }

    override fun onClickDecline(match: MatchInfo) {}

    override fun onClickAccept(match: MatchInfo) {}

    override fun onClickDelete(match: MatchInfo) {}

    private val onItemClickListener: RecyclerItemAdapter.ItemClickListener by lazy {
        object : RecyclerItemAdapter.ItemClickListener {
            override fun onItemClick(parent: View?, v: View, position: Int, entity: Any) {
                MatchInfoDialog.show(activity.fragmentManager, entity as MatchInfo, this@AllRequestsFragment)
            }
        }
    }

    /********* Setup *********/

    override fun bind() {
        presenter.view = this
    }

    override fun prepareData() {
        activity.title = "Moji requestovi"

        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        list.adapter = adapter
    }

    override fun subscribeForUIEvents() {
        adapter.listener = onItemClickListener
    }

    override fun injectDependencies() {
        baseActivity.injector.inject(this)
    }

    override fun load() {
        presenter.getAllRequests()
    }

    /********* Companion *********/

    companion object {
        fun newInstance() = AllRequestsFragment()
    }

}
