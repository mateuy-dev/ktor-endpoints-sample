

// https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleVersions)
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
