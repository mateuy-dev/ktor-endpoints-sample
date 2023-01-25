
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}


group = "dev.trito.endpoints.client"
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
                api(project(":data"))
                implementation(libs.bundles.ktor.client)
                implementation(libs.coroutines.core)
                implementation(libs.serialization.json)
                implementation(libs.logback.classic)
            }
        }
        val commonTest by getting{
            dependencies{
                implementation(kotlin("test"))
                implementation(libs.coroutines.test)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okHttp)
            }
        }
        val jsMain by getting{
            dependencies {

            }
        }
    }
}
