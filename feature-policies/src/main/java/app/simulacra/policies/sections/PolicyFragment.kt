package app.simulacra.policies.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.simulacra.featurecommon.injection.utils.Injectable
import app.simulacra.featurecommon.injection.viewmodel.SimpleViewModelProviderFactory
import app.simulacra.featurecommon.injection.viewmodel.viewModel
import app.simulacra.featurecommon.utils.lazy.unsafeLazy
import app.simulacra.policies.R
import javax.inject.Inject
import javax.inject.Provider

class PolicyFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<PolicyViewModel>

    private val viewModel by unsafeLazy {
        viewModel<PolicyViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.policy_fragment, container, false)
    }

}