plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("dagger.hilt.android.plugin")
        id("kotlin-kapt")
}


android {
    namespace = "com.example.randomdogsassignment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.randomdogsassignment"
        minSdk = 26
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes +=  "/META-INF/gradle/incremental.annotation.processors"
        }
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
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
        // Dagger Hilt for Dependency Injection
      //  implementation(libs.hilt.android)

        //implementation(libs.hilt.android.compiler)

    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)

    implementation (libs.androidx.hilt.navigation.compose)
    kapt (libs.androidx.hilt.compiler)
  //  implementation(libs.androidx.hilt.lifecycle.viewmodel)


    // Lifecycle ViewModel dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Jetpack Compose navigation
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    // Activity and Compose integration
    implementation ("androidx.activity:activity-compose:1.8.2")

    // Coil for image loading in Compose
    implementation ("io.coil-kt:coil-compose:2.5.0")



        // Jetpack Compose
        implementation("androidx.compose.ui:ui:1.5.0")
        implementation("androidx.compose.material:material:1.5.0")
       // implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
        implementation("androidx.activity:activity-compose:1.7.2")

        // Navigation
        implementation("androidx.navigation:navigation-compose:2.6.0")

        // ViewModel & LiveData
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")




    //implementation(libs.coilcompose)
    implementation ("io.coil-kt:coil-compose:1.3.2") // For image loading
   // implementation("com.squareup.coil:coil-compose:2.3.0") // For image loading
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.5.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")



}