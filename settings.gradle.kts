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
include(":feature")
include(":feature:bikeNetwork")
include(":feature:bikeNetwork:domain")
include(":core:network")
include(":feature:bikeNetwork:data")
include(":feature:bikeNetwork:presentation")
include(":core:uiWidgets")
