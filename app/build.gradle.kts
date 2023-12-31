plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")

}

android {
    namespace = "com.example.boyner"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.boyner"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()

    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation ("androidx.room:room-runtime:2.5.2")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.ads:mediation-test-suite:3.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.lifecycle:lifecycle-service:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.room:room-runtime:2.5.2")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.google.android.gms:play-services-ads:22.2.0")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.6.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.6.0")
    implementation ("ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:1.0.6")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
}