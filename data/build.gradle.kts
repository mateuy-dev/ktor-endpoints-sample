import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "dev.trito.endpoints.data"
version = "0.0.1"

kotlin {
    jvm() {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.serialization.json)
                implementation(libs.ktor.resources)
            }
        }
        val commonTest by getting{
            dependencies {

            }
        }


        val jvmMain by getting {

        }
        val jsMain by getting{
        }
    }
}
