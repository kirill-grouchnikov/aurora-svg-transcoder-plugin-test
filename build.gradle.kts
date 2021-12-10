buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.0.0")
        classpath(kotlin("gradle-plugin", version = "1.5.31"))
        classpath("org.pushing-pixels:aurora-tools-svg-transcoder-gradle-plugin:1.0.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }

    configurations {
        all {
            exclude(group = "org.jetbrains.compose.material", module = "material")
        }
    }
}
