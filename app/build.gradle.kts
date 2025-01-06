import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)
}

val environment by extra("environment")

val firebaseProperties = Properties().apply {
    val propertiesFile = rootProject.file("secrets/firebase.properties")
    if (propertiesFile.exists()) {
        load(FileInputStream(propertiesFile))
    } else {
        throw GradleException("Properties file not found: ${propertiesFile.absolutePath}")
    }
}

android {
    namespace = "com.msapps.buzzchat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.msapps.buzzchat"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions += listOf(environment)

    productFlavors {
        create("develop") {
            dimension = environment

            buildConfigField("String","FIREBASE_AUTH_BASE_URL", firebaseProperties.getProperty("authBaseUrl"))
            buildConfigField("String","FIREBASE_API_KEY", firebaseProperties.getProperty("apiKey"))
        }
        create("staging") {
            dimension = environment

            buildConfigField("String","FIREBASE_AUTH_BASE_URL", firebaseProperties.getProperty("authBaseUrl"))
            buildConfigField("String","FIREBASE_API_KEY", firebaseProperties.getProperty("apiKey"))
        }
        create("production") {
            dimension = environment

            buildConfigField("String","FIREBASE_AUTH_BASE_URL", firebaseProperties.getProperty("authBaseUrl"))
            buildConfigField("String","FIREBASE_API_KEY", firebaseProperties.getProperty("apiKey"))
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Koin
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Country picker
    implementation (libs.ccp)

}