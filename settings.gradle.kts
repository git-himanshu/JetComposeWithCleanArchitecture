pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JetComposeWithCleanArchitecture"
include(":app")
include(":core")
include(":core:common")
include(":domain")
include(":core:network")
include(":data")
include(":presentation")
include(":core:uiWidgets")
include(":core:testing")
