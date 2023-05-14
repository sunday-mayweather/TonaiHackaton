package app.simulacra.sections.home

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.os.PowerManager
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import app.simulacra.R
import app.simulacra.featurecommon.base.OnBackPressedHandler
import app.simulacra.featurecommon.utils.build.isDevBuild
import app.simulacra.featurecommon.utils.views.applyTextColors
import app.simulacra.featurecommon.utils.views.gone
import app.simulacra.featurecommon.utils.views.visible
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(R.layout.activity_main), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navListener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        navListener = initNavListener()
        navController.addOnDestinationChangedListener(navListener)
        if (isDevBuild) {
            wakeUp()
        }
    }

    override fun onDestroy() {
        navController.removeOnDestinationChangedListener(navListener)
        super.onDestroy()
    }

    override fun onBackPressed() {
        val currentFragment = navHostFragment.childFragmentManager.primaryNavigationFragment
        if (currentFragment is OnBackPressedHandler) {
            currentFragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private fun initNavListener(): NavController.OnDestinationChangedListener {
        return NavController.OnDestinationChangedListener { _, destination, _ ->
           // colorStatusBar(destination.id)
            bottomNavigationView.post {
                when (destination.id) {
                    R.id.discoverFragment, R.id.profileFragment -> {
                        bottomNavigationView.visible()
                        updateNavigationMenu()
                    }
                    else -> {
                        bottomNavigationView.gone()
                    }
                }
            }
            // As soon as any fragment except for splash shows up we can safely change window background
            // checking for LayerDrawable to avoid redundant background changes
            if (destination.id != R.id.fragmentSplash
                && window.decorView.background is LayerDrawable
            ) {
                window.setBackgroundDrawableResource(R.color.color_on_primary)
            }
        }
    }

    @Suppress("unused")
    private fun colorStatusBar(@IdRes fragmentId: Int) {
        window.statusBarColor = when (fragmentId) {
            R.id.discoverFragment, R.id.profileFragment -> {
                ResourcesCompat.getColor(resources, R.color.almost_black, theme)
            }
            else -> {
                ResourcesCompat.getColor(resources, R.color.main_black, theme)
            }
        }
    }

    private fun updateNavigationMenu() {
        val accentColor = ResourcesCompat.getColor(this.resources, R.color.pure_gold, null)
        val blackColor = ResourcesCompat.getColor(this.resources, R.color.almost_black, null)
        bottomNavigationView.applyTextColors(checked = accentColor, unchecked = blackColor)
    }

    @Suppress("DEPRECATION")
    private fun wakeUp() {
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        val power = getSystemService<PowerManager>()!!
        val lock = power.newWakeLock(
            PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP
                    or PowerManager.ON_AFTER_RELEASE, "simulacra:wakeup")
        lock.acquire(1000)
        lock.release()
    }

}