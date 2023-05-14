package app.simulacra.activequest.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import javax.inject.Inject
import javax.inject.Provider
import androidx.fragment.app.Fragment
import app.simulacra.activequest.R
import app.simulacra.featurecommon.injection.utils.Injectable
import app.simulacra.featurecommon.injection.viewmodel.SimpleViewModelProviderFactory
import app.simulacra.featurecommon.injection.viewmodel.viewModel
import app.simulacra.featurecommon.utils.lazy.unsafeLazy
import app.simulacra.featurecommon.utils.views.handleBackPressed
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class ActiveQuestFragment : Fragment(R.layout.active_quest_fragment), Injectable {

    @Inject
    lateinit var viewModelProvider: Provider<ActiveQuestViewModel>

    lateinit var sceneView: ArSceneView

    data class Model(
        val fileLocation: String,
        val scaleUnits: Float? = null,
        val placementMode: PlacementMode = PlacementMode.BEST_AVAILABLE,
        val applyPoseRotation: Boolean = true
    )

    val models = listOf(
        Model(
            fileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/Tiger/model.glb",
            // Display the Tiger with a size of 3 m long
            scaleUnits = 2.5f,
            placementMode = PlacementMode.BEST_AVAILABLE,
            applyPoseRotation = false
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/DamagedHelmet.glb",
            placementMode = PlacementMode.INSTANT,
            scaleUnits = 0.5f
        ),
        Model(
            fileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/GiantPanda/model.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL,
            // Display the Tiger with a size of 1.5 m height
            scaleUnits = 1.5f
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/Spoons.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL_AND_VERTICAL,
            // Keep original model size
            scaleUnits = null
        ),
        Model(
            fileLocation = "https://sceneview.github.io/assets/models/Halloween.glb",
            placementMode = PlacementMode.PLANE_HORIZONTAL,
            scaleUnits = 2.5f
        ),
    )
    var modelNode: ArModelNode? = null

    private val viewModel by unsafeLazy {
        viewModel<ActiveQuestViewModel>(SimpleViewModelProviderFactory(viewModelProvider))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sceneView = view.findViewById(R.id.sceneView)
        handleBackPressed { requireActivity().finish() }
        newModelNode()
    }

    fun placeModelNode() {
        modelNode?.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    fun newModelNode() {
        modelNode?.takeIf { !it.isAnchored }?.let {
            sceneView.removeChild(it)
            it.destroy()
        }
        val model = models.random()
        modelNode = ArModelNode(model.placementMode).apply {
            applyPoseRotation = model.applyPoseRotation
            loadModelAsync(
                context = requireContext(),
                lifecycle = lifecycle,
                glbFileLocation = model.fileLocation,
                autoAnimate = true,
                scaleToUnits = model.scaleUnits,
                // Place the model origin at the bottom center
                centerOrigin = Position(y = -1.0f)
            ) {
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = { node, _ ->
               // placeModelButton.isGone = node.isAnchored
            }
            onHitResult = { node, _ ->
               // placeModelButton.isGone = !node.isTracking
            }
        }
        sceneView.addChild(modelNode!!)
        // Select the model node by default (the model node is also selected on tap)
        sceneView.selectedNode = modelNode
    }
}