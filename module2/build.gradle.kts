import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    dependencies {
        classpath(libs.aurora.svgtranscoder.gradlePlugin)
    }
}

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
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

tasks.withType<KotlinCompile> {
    doFirst {
        task<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeTask>("transcodeSingle") {
            inputDirectory = file("src/desktopMain/resources")
            outputDirectory = file("src/gen/kotlin/org/aurora/demo/svg2")
            outputPackageName = "org.aurora.demo.svg2"
            transcode()
        }

        task<org.pushingpixels.aurora.tools.svgtranscoder.gradle.TranscodeDeepTask>("transcodeFolder") {
            inputRootDirectory = file("src/desktopMain/resources")
            outputRootDirectory = file("src/gen/kotlin/org/aurora/demo/scalable/svg2")
            outputRootPackageName = "org.aurora.demo.scalable.svg2"
            transcode()
        }
    }
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
