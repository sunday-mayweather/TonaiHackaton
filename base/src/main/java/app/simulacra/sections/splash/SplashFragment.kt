package app.simulacra.sections.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.simulacra.R
import app.simulacra.featurecommon.injection.utils.Injectable
import app.simulacra.featurecommon.injection.viewmodel.SimpleViewModelProviderFactory
import app.simulacra.featurecommon.injection.viewmodel.viewModel
import app.simulacra.featurecommon.utils.lazy.unsafeLazy
import javax.inject.Inject
import javax.inject.Provider

class SplashFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<SplashViewModel>

    private val viewModel by unsafeLazy {
        viewModel<SplashViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        // important to trigger this after onResume and not in onViewCreated so the navigation logic happens
        // in the foreground (fragments limitation, no more opening app and seeing just a white screen)
        Handler(requireNotNull(Looper.myLooper())).postDelayed({
            viewModel.handleAppLaunch()
        }, 1000L)
    }
}