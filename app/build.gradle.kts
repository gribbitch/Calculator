plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.calculator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.calculator"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    // Navigation
    implementation("androidx.navigation:navigation-fragment:2.6.0")
    implementation("androidx.navigation:navigation-ui:2.6.0")
    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.6.0")
    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.6.0")
    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.6.0")

    // Fragment
    implementation("androidx.fragment:fragment:1.7.1")

}