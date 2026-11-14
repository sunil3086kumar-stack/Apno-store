plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.store.apno"
    compileSdk = 36

    defaultConfig {
        applicationId = "in.apnostore.app"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        fun envOrDefault(name: String, fallback: String): String {
            return System.getenv(name)?.takeIf { it.isNotBlank() } ?: fallback
        }
        create("release") {
            storeFile = file(envOrDefault("KEYSTORE_FILE", "keystore/keystore.jks"))
            storePassword = envOrDefault("KEYSTORE_PASSWORD", "androidpassword")
            keyAlias = envOrDefault("KEY_ALIAS", "myalias")
            keyPassword = envOrDefault("KEY_PASSWORD", "androidpassword")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidbrowserhelper)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.app.update.ktx)
}
