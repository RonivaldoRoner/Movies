import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ronivaldoroner.movies.remote"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        val theMovieDbAPIKey: String = (
                gradleLocalProperties(rootDir, providers).getProperty("TheMovieDbAPIKey")
                    ?: "Key not found"
                ).toString()

        val theMovieDbAccessKey: String = (
                gradleLocalProperties(rootDir, providers).getProperty("TheMovieDbAccessKey")
                    ?: "Key not found"
                ).toString()

        all {
            buildConfigField("String", "TheMovieDbAPIKey", theMovieDbAPIKey)
            buildConfigField("String", "TheMovieDbAccessKey", theMovieDbAccessKey)
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.loggin)
    implementation(libs.kotlinx.serialization)
    implementation(libs.serialization.converter)
    implementation(libs.retrofit.converter)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}