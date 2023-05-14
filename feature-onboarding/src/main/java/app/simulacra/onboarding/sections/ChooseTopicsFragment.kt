package app.simulacra.onboarding.sections

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
import app.simulacra.featurecommon.utils.livedata.skip
import app.simulacra.onboarding.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.choose_topics_fragment.btnContinue
import kotlinx.android.synthetic.main.choose_topics_fragment.chipGroupFilter
import kotlinx.android.synthetic.main.welcome_fragment.rootLayout
import javax.inject.Inject
import javax.inject.Provider

class ChooseTopicsFragment  : Fragment(), Injectable  {

    @Inject
    lateinit var viewModelProvider: Provider<ChooseTopicsViewModel>

    private val observer = Observer<ChooseTopicState> {
        renderState(it)
    }

    private val viewModel by unsafeLazy {
        viewModel<ChooseTopicsViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_topics_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnContinue.setOnClickListener { viewModel.continueClick() }
        chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.updateTopics(checkedIds)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.contentLiveData.skip(1).observe(viewLifecycleOwner, observer)
    }

    override fun onStop() {
        viewModel.contentLiveData.removeObserver(observer)
        super.onStop()
    }

    private fun renderState(state: ChooseTopicState) {
        when (state) {
            is ChooseTopicState.TopicsEmpty -> {
                Snackbar.make(rootLayout, getString(R.string.onboarding_failed_to_continue), Snackbar.LENGTH_LONG)
                    .show()
            }
            else -> {
                // No op
            }
        }
    }
}