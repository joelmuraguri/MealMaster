package com.joel.mealmaster.utils

import org.gradle.api.JavaVersion

object ProjectConfig {

    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24
    const val versionName = "1.0"
    const val versionCode = 1
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    const val jvmTarget = "17"
    const val kotlinCompilerExtensionVersion = "1.5.3"

}