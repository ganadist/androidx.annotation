import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.1.20"
    id("maven-publish")
}

kotlin {
    jvm().compilations.all {
        compilerOptions.configure {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib:2.1.20")
            }
        }
    }
}

tasks.named<Jar>("jvmJar") {
    // Strip out typedef classes. For Android libraries, this is done
    // automatically by the Gradle plugin, but the Annotation library is a
    // plain jar, built by the regular Gradle java plugin. The typedefs
    // themselves have been manually extracted into the
    // external-annotations directory, and those are packaged separately
    // below by the annotationsZip task.
    exclude("androidx/annotation/ProductionVisibility.class")
    exclude("androidx/annotation/DimensionUnit.class")
}

group = "androidx.annotation"
version = "1.10.0"
