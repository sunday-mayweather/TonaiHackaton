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
import app.simulacra.featurecommon.utils.views.applyPoliciesFormatting
import app.simulacra.onboarding.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.welcome_fragment.btnContinue
import kotlinx.android.synthetic.main.welcome_fragment.rootLayout
import kotlinx.android.synthetic.main.welcome_fragment.tvPolicies
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class WelcomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<WelcomeViewModel>

    private val observer = Observer<WelcomeState> {
        renderState(it)
    }

    private val viewModel by unsafeLazy {
        viewModel<WelcomeViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.contentLiveData.skip(1).observe(viewLifecycleOwner, observer)
    }

    override fun onStop() {
        viewModel.contentLiveData.removeObserver(observer)
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPolicies.applyPoliciesFormatting(
            termsClickListener = {
                Timber.d("Terms clicked!")
                viewModel.policyClick()
            }, privacyPolicyClickListener = {
                Timber.d("Privacy policy clicked!")
                viewModel.policyClick()
            })
        btnContinue.setOnClickListener { viewModel.continueClick() }
    }

    private fun renderState(state: WelcomeState) {
        when (state) {
            is WelcomeState.Loading -> {
            }
            is WelcomeState.Error.NetworkError -> {
                Snackbar.make(rootLayout, getString(R.string.error_no_internet), Snackbar.LENGTH_LONG)
                    .show()
            }
            is WelcomeState.Error.UnknownError -> {
                Snackbar.make(rootLayout, getString(R.string.error_unknown), Snackbar.LENGTH_LONG)
                    .show()
            }
            is WelcomeState.Error.ServerError -> {
                Snackbar.make(rootLayout, state.errorMessage, Snackbar.LENGTH_LONG).show()
            }
            is WelcomeState.Success -> {
            }
        }
    }
}