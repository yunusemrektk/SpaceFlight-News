plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.study.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
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
    implementation(libs.androidx.material3)

    //Dependency Injection
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    debugImplementation(libs.ui.tooling)
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // For instrumented tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.48.1")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    kaptTest("com.google.dagger:hilt-compiler:2.48.1")


    //Module
    implementation(project(":common:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    //Navigtaion
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}