plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ktlint.gradle)
    alias(libs.plugins.detekt)
}

android {
    namespace = "dev.caiofaustino.jabwa"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.caiofaustino.jabwa"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.mvi)

    implementation(libs.androidx.core.ktx)?.because("Better support for older Android versions")
    implementation(libs.androidx.lifecycle.viewmodel)?.because("Adds ViewModel and sub-utilities.")
    implementation(libs.androidx.lifecycle.viewmodel.compose)?.because("ViewModel utilities for Compose")
    implementation(libs.androidx.lifecycle.runtime.compose)?.because("Lifecycle utilities for Compose")

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bouncycastle.bcprov.jdk15on)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.espresso.core)
}
