// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

buildscript {
    val kotlinVersion = "1.9.24"

    repositories {
        google()  // Inclure le dépôt Google pour les plugins Android et Firebase
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.7.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.gms:google-services:4.4.2")  // Dépendance Google Services
    }
}

allprojects {
    repositories {
        //google()  //Dépôt Google pour Firebase et autres services
      //  mavenCentral()
    }
}
