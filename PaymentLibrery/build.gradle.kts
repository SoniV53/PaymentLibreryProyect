plugins {
    id("com.android.library")
    id ("kotlin-android")
    `maven-publish`
}

group = "com.github.jitpack"
version = "1.0"

android {
    namespace = "com.control.paymentlibrery"
    compileSdk = 34

    defaultConfig {
        minSdk = 30

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.SoniV53"
                artifactId = "PaymentLibreryProyect"
                version = "1.0.1"
            }
        }
    }
}