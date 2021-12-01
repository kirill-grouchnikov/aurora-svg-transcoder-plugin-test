import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:1.0.0-beta4")
    }
}

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.pushing-pixels.aurora.tools.svgtranscoder.gradle")
}

kotlin {
    jvm("desktop")
    sourceSets {
        named("desktopMain") {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.desktop.common)
            }
        }
    }
}

tasks.register<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeTask>("transcodeSingle") {
    inputDirectory = file("src/desktopMain/resources")
    outputDirectory = file("src/desktopMain/kotlin/org/aurora/demo/svg")
    outputPackageName = "org.aurora.demo.svg"
    transcode()
}

tasks.register<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeDeepTask>("transcodeFolder") {
    inputRootDirectory = file("src/desktopMain/resources/scalable")
    outputRootDirectory = file("src/desktopMain/kotlin/org/aurora/demo/scalable/svg")
    outputRootPackageName = "org.aurora.demo.scalable.svg"
    transcode()
}

tasks.withType<KotlinCompile> {
    dependsOn("transcodeSingle")
    dependsOn("transcodeFolder")
}
