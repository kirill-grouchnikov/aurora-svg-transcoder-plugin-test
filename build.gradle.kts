plugins {
    kotlin("jvm") version "2.0.10"
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    dependencies {
        classpath(libs.compose.desktop)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.aurora.svgtranscoder.gradlePlugin)
        classpath(libs.versionchecker.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    configurations {
        all {
            exclude(group = "org.jetbrains.compose.material", module = "material")
        }
    }
}
