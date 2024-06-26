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

rootProject.name = "MealMaster"
include(":app")
include(":feature:preference")
include(":feature:profile")
include(":feature:recipes")
include(":feature:discover")
include(":feature:mealplan")
include(":core:database")
include(":core:utilities")
include(":core:data")
include(":core:network")
include(":core:domain")
include(":feature:onboarding")
