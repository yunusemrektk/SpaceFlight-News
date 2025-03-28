pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Spaceflight News App"
include(":app")
include(":feature")
include(":feature:main")
include(":feature:favorite")
include(":common")
include(":common:ui")
include(":feature:detail")
include(":feature:summary")
include(":core")
include(":core:data")
include(":core:datastore")
include(":core:domain")
include(":core:network")
include(":core:model")
include(":core:mymodel")
include(":core:testing")
