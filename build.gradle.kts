import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
val properties: Properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}
buildscript {
    repositories {
    }
}

allprojects {
    repositories {
    }
}
tasks.register("clean") {
    doLast {
        project.rootProject.buildDir.deleteRecursively()
    }
}