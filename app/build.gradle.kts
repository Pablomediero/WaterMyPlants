plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.realm.kotlin")
}

android {
    namespace = "pmediero.com"
    compileSdk = 34

    defaultConfig {
        applicationId = "pmediero.com"
        minSdk = 24
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
        }
    }
}

dependencies {
    implementation(Dependencies.core)
    implementation(Dependencies.lifecycle)
    implementation(Dependencies.activity_compose)
    implementation(platform(Dependencies.compose_boom))
    implementation(Dependencies.ui)
    implementation(Dependencies.ui_graphics)
    implementation(Dependencies.ui_tooling)
    implementation(Dependencies.material)

    implementation(Dependencies.lottie)
    implementation(Dependencies.splash)
    implementation(Dependencies.navigation)
    implementation(Dependencies.fonts)

    implementation(Dependencies.koin)
    implementation(Dependencies.koincompose)

    implementation(Dependencies.realmDb)

    implementation(Dependencies.coil)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_expresso)
    androidTestImplementation(platform(Dependencies.test_compose_boom))
    androidTestImplementation(Dependencies.test_ui_junit)
    debugImplementation(Dependencies.test_ui_tooling)
    debugImplementation(Dependencies.test_ui_manifest)


}