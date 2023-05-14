package app.simulacra.featurecommon.utils.build

import app.simulacra.featurecommon.BuildConfig

const val DEV_BUILD = "debug"
const val BETA_BUILD = "beta"
const val RELEASE_BUILD = "release"

val isDevBuild: Boolean
    get() = BuildConfig.BUILD_TYPE == DEV_BUILD

val isBetaBuild: Boolean
    get() = BuildConfig.BUILD_TYPE == BETA_BUILD

val isReleaseBuild: Boolean
    get() = BuildConfig.BUILD_TYPE == RELEASE_BUILD
