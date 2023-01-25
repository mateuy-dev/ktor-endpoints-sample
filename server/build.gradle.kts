plugins {
    kotlin("multiplatform")
    application
}

group = "dev.trito.endpoints.server"
version = "0.0.1"


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":data"))
                implementation(libs.serialization.json)
                implementation(libs.ktor.server.websockets)
                implementation(libs.ktor.server.netty)
                implementation(libs.ktor.server.contentNegotiation)
                implementation(libs.ktor.server.cors)
                implementation(libs.ktor.server.resources)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.server.auth)
                implementation(libs.ktor.server.auth.jwt)
                implementation(libs.logback.classic)
                implementation(libs.coroutines.core)
                implementation("org.slf4j:jcl-over-slf4j:1.7.30")
            }
        }
        val jvmTest by getting{
            dependencies{
                implementation(project(":client"))
                implementation(kotlin("test"))
                implementation(libs.ktor.server.test.host)
                implementation(libs.bundles.ktor.client)
                implementation(libs.koin.test)
            }
        }
    }
}

application {
    mainClass.set("dev.trito.endpoints.server.ServerKt")
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}