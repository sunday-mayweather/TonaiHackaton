package app.simulacra.discover.sections.details

import android.graphics.Paint
import javax.inject.Inject
import javax.inject.Provider
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import app.simulacra.discover.R
import app.simulacra.featurecommon.injection.utils.Injectable
import app.simulacra.featurecommon.injection.viewmodel.SimpleViewModelProviderFactory
import app.simulacra.featurecommon.injection.viewmodel.viewModel
import app.simulacra.featurecommon.utils.context.openLink
import app.simulacra.featurecommon.utils.lazy.unsafeLazy
import kotlinx.android.synthetic.main.story_details_fragment.tvDescription
import kotlinx.android.synthetic.main.story_details_fragment.tvSource
import kotlinx.android.synthetic.main.story_details_fragment.tvTitle

class StoryDetailsFragment : Fragment(R.layout.story_details_fragment), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<StoryDetailsViewModel>

    private val observer = Observer<StoryDetailsState> {
        renderState(it)
    }

    private val args by navArgs<StoryDetailsFragmentArgs>()

    private val viewModel by unsafeLazy {
        viewModel<StoryDetailsViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadStoryDetails(args.sourceUrl)
        tvSource.paintFlags = tvSource.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onStart() {
        super.onStart()
        viewModel.contentLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun onStop() {
        viewModel.contentLiveData.removeObserver(observer)
        super.onStop()
    }

    private fun renderState(state: StoryDetailsState) {
        when (state) {
            is StoryDetailsState.Error -> {
                // Handle it somehow
            }
            is StoryDetailsState.Loaded -> {
                with(state.data) {
                    tvTitle.text = title
                    tvSource.text = sourceUrl
                    tvDescription.text = summary
                    tvSource.setOnClickListener {
                        requireContext().openLink(sourceUrl)
                    }
                }
            }
        }
    }
}