plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


//buildscript {
//    ext.android_gradle_plugin_version = '7.2.0'
//    ext.kotlin_version = '1.6.10'
//    ext.detekt_gradle_plugin_version = "1.13.1"
//    ext.spotbugs_gradle_plugin_version = "4.5.1"
//    ext.dokka_version = "1.4.20"
//
//    repositories {
//        google()
//        mavenCentral()
//        maven { url "https://plugins.gradle.org/m2/" }
//    }
//
//    dependencies {
//        classpath "com.android.tools.build:gradle:$android_gradle_plugin_version"
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
//        classpath "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detekt_gradle_plugin_version"
//        classpath "gradle.plugin.com.github.spotbugs.snom:spotbugs-gradle-plugin:$spotbugs_gradle_plugin_version"
//        classpath "org.jetbrains.dokka:dokka-gradle-plugin:$dokka_version"
//    }
//}
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
