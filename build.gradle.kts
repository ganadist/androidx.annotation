import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.9.23"
    id("maven-publish")
}

kotlin {
    jvm().compilations.all {
        compilerOptions.configure {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
            }
        }
    }
}

group = "androidx.annotation"
version = "1.8.2"
