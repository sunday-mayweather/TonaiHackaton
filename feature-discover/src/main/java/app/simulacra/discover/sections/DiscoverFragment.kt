package app.simulacra.discover.sections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.simulacra.discover.R
import app.simulacra.featurecommon.injection.utils.Injectable
import app.simulacra.featurecommon.injection.viewmodel.SimpleViewModelProviderFactory
import app.simulacra.featurecommon.injection.viewmodel.viewModel
import app.simulacra.featurecommon.utils.lazy.unsafeLazy
import app.simulacra.featurecommon.utils.livedata.skip
import app.simulacra.featurecommon.utils.views.gone
import app.simulacra.featurecommon.utils.views.handleBackPressed
import app.simulacra.featurecommon.utils.views.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.discover_fragment.loadingProgress
import kotlinx.android.synthetic.main.discover_fragment.rootLayout
import kotlinx.android.synthetic.main.discover_fragment.summariesList
import javax.inject.Inject
import javax.inject.Provider

class DiscoverFragment : Fragment(R.layout.discover_fragment), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<DiscoverViewModel>

    private val observer = Observer<DiscoverState> {
        renderState(it)
    }

    private val adapter = SummariesAdapter {
        viewModel.openSummary(it)
    }

    private val viewModel by unsafeLazy {
        viewModel<DiscoverViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleBackPressed { requireActivity().finish() }
        summariesList.adapter = adapter
        summariesList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.loadSummaries()
    }

    override fun onStart() {
        super.onStart()
        viewModel.contentLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun onStop() {
        viewModel.contentLiveData.removeObserver(observer)
        super.onStop()
    }

    override fun onDestroyView() {
        summariesList.adapter = null
        summariesList.layoutManager = null
        super.onDestroyView()
    }

    private fun renderState(state: DiscoverState) {
        when (state) {
            is DiscoverState.Empty -> {
                loadingProgress.gone()
                summariesList.gone()
            }
            is DiscoverState.Loading -> {
                summariesList.gone()
                loadingProgress.visible()
            }
            is DiscoverState.Error -> {
                loadingProgress.gone()
                Snackbar.make(rootLayout, getString(R.string.error_unknown), Snackbar.LENGTH_LONG)
                    .show()
                summariesList.gone()
            }
            is DiscoverState.Loaded -> {
                loadingProgress.gone()
                summariesList.visible()
                adapter.submitList(state.data)
            }
        }
    }
}