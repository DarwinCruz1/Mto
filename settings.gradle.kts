pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.11.1" apply false
        id("com.android.library") version "8.11.1" apply false
        id("org.jetbrains.kotlin.android") version "1.9.22" apply false
        id("com.google.devtools.ksp") version "1.9.22-1.0.17" // Reemplaza con la versión correcta si es necesario
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ServicioTecnicoApp"
include(":app")


