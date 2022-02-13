import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:1.0.1")
    }
}

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.pushing-pixels.aurora.tools.svgtranscoder.gradle")
    idea
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
    outputDirectory = file("src/gen/kotlin/org/aurora/demo/svg")
    outputPackageName = "org.aurora.demo.svg"
    transcode()
}

tasks.register<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeDeepTask>("transcodeFolder") {
    inputRootDirectory = file("src/desktopMain/resources/scalable")
    outputRootDirectory = file("src/gen/kotlin/org/aurora/demo/scalable/svg")
    outputRootPackageName = "org.aurora.demo.scalable.svg"
    transcode()
}

tasks.withType<KotlinCompile> {
    dependsOn("transcodeSingle")
    dependsOn("transcodeFolder")
}

kotlin {
    sourceSets {
        kotlin {
            sourceSets["desktopMain"].apply {
                kotlin.srcDir("$rootDir/src/desktopMain/kotlin")
                kotlin.srcDir("$rootDir/src/gen/kotlin")
            }
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("$rootDir/src/gen/kotlin"))
    }
}
