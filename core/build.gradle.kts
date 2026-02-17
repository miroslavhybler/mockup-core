plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization.json)
    `maven-publish`
}

group = "com.github.miroslavhybler.mockup-core"
version = "2.0.0-alpha02"

android {
    namespace = "com.mockup.core"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
    kotlin {
        jvmToolchain(jdkVersion = 11)
    }
    publishing {
        multipleVariants {
            withSourcesJar()
            withJavadocJar()
        }
        singleVariant("release") {

        }
    }
}



dependencies {
    /** Mockup plugin */
    //Always keep same version for processor and annotations
    compileOnly("com.github.miroslavhybler:ksp-mockup-annotations:2.0.0-alpha02")
    implementation(platform(libs.compose.bom))
    compileOnly(libs.compose.ui.tooling.preview)
    implementation(libs.core.ktx)
    implementation(libs.serialization.json)
    implementation(libs.kotlin.reflect)

    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.compose.ui.tooling)
}


afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components.getByName("release"))
                groupId = "com.github.miroslavhybler"
                artifactId = "mockup-core"
                version = "2.0.0-alpha02"
                pom {
                    description.set("Jitpack.io deploy")
                }
            }

        }
        repositories {
            mavenLocal()
        }
    }
}