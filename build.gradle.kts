plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hilt) apply false

    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
}