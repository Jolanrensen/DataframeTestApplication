import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlinx.dataframe") version "0.11.0-dev" // TODO CHANGE TO RELEASE VERSION IF AVAILABLE
}

android {
    namespace = "nl.jolanrensen.dataframeTestApplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "nl.jolanrensen.dataframeTestApplication"
        minSdk = 26 // O+
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += listOf(
                "META-INF/kotlin-jupyter-libraries/libraries.json",
                "META-INF/{AL2.0,LGPL2.1,ASL-2.0.txt,INDEX.LIST,DEPENDENCIES,LICENSE.md,NOTICE.md,LGPL-3.0.txt}",
                "{draftv3,draftv4}/schema",
                "arrow-git.properties",
            )
        }
    }
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }


dependencies {
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")

    implementation("org.jetbrains.kotlinx:dataframe:0.11.0-dev") // TODO CHANGE TO RELEASE VERSION IF AVAILABLE

    implementation(platform("androidx.compose:compose-bom:2023.04.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.04.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}