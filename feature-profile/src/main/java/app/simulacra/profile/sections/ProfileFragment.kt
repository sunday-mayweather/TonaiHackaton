package app.simulacra.profile.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.simulacra.featurecommon.injection.utils.Injectable
import app.simulacra.featurecommon.injection.viewmodel.SimpleViewModelProviderFactory
import app.simulacra.featurecommon.injection.viewmodel.viewModel
import app.simulacra.featurecommon.utils.lazy.unsafeLazy
import app.simulacra.profile.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.profile_fragment.chipGroupFilter
import kotlinx.android.synthetic.main.profile_fragment.rootLayout
import javax.inject.Inject
import javax.inject.Provider

class ProfileFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<ProfileViewModel>

    private val viewModel by unsafeLazy {
        viewModel<ProfileViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    private val observer = Observer<ProfileState> {
        renderState(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTopics()
        chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.updateTopics(checkedIds)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.contentLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun onStop() {
        viewModel.contentLiveData.removeObserver(observer)
        super.onStop()
    }

    private fun renderState(state: ProfileState) {
        when (state) {
            is ProfileState.TopicsEmpty -> {
                Snackbar.make(rootLayout, getString(R.string.onboarding_failed_to_continue), Snackbar.LENGTH_LONG).show()
            }
            is ProfileState.UpdatedTopicsState -> {
                state.topics.forEach {
                    chipGroupFilter.check(it.resId)
                }
            }
        }
    }
}