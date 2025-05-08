import org.codehaus.groovy.runtime.ArrayTypeUtils.dimension
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.luffy18346.amexdemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.luffy18346.amexdemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
    }

    // Build Types for different url per different environment
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
//            buildConfigField("String", "BASE_URL", "\"https://dev.api.com/\"")
        }
//        create("betaRelease") {
//            isMinifyEnabled = false
//            isDebuggable = false
//            buildConfigField("String", "BASE_URL", "\"https://dev.api.com/\"")
//        }
        release {
            isMinifyEnabled = false
//            buildConfigField("String", "BASE_URL", "\"https://dev.api.com/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    buildFeatures {
        compose = true

        // Disable unused AGP features
        buildConfig = true
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

//    flavorDimensions += "environment"

    // Product Flavours
//    productFlavors {
//        create("dev") {
//            dimension = "environment"
//            buildConfigField("String", "BASE_URL", "\"https://dev.api.com/\"")
//            applicationIdSuffix = ".dev"
//            versionNameSuffix = "-dev"
//        }
//        create("staging") {
//            dimension = "environment"
//            buildConfigField("String", "BASE_URL", "\"https://staging.api.com/\"")
//            applicationIdSuffix = ".staging"
//            versionNameSuffix = "-staging"
//        }
//        create("prod") {
//            dimension = "environment"
//            buildConfigField("String", "BASE_URL", "\"https://api.com/\"")
//        }
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Retrofit + Moshi + OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.logging.interceptor)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(kotlin("test"))
}