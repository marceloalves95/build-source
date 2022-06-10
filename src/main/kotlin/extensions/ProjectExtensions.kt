package extensions

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * @author RubioAlves
 * Created 24/04/2022 at 13:39
 */

internal val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module")

internal val Project.publishing: PublishingExtension
    get() = extensions.findByName("publishing") as? PublishingExtension
        ?: error("Extensions publishing not found")

fun Project.applyPlugins() {
    with(plugins) {
        apply("com.android.library")
        apply("kotlin-android")
    }
}

fun Project.applyAndroid() {
    with(android) {
        compileSdkVersion(AppConfig.compileSdkVersion)
        defaultConfig {
            minSdk = AppConfig.minSdkVersion
            targetSdk = AppConfig.targetSdkVersion
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            getByName("release") {
                minifyEnabled(false)
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

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        viewBinding.isEnabled = true

    }
}

@Suppress("UnstableApiUsage")
fun Project.applyAndroidCompose() {
    with(android) {
        compileSdkVersion(AppConfig.compileSdkVersion)
        defaultConfig {
            minSdk = AppConfig.minSdkVersion
            targetSdk = AppConfig.targetSdkVersion
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            vectorDrawables.useSupportLibrary = true

        }

        buildTypes {
            getByName("release") {
                minifyEnabled(false)
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

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        composeOptions.kotlinCompilerExtensionVersion = Version.compose_version

        buildFeatures.compose = true
    }

}

fun Project.mavenPublishLocal(
    groupId: String,
    artifactId: String,
    version: String
) {

    (android as LibraryExtension).publishing {

        val sourcesJar by tasks.creating(Jar::class) {
            archiveClassifier.set("sources")
            from(android.sourceSets.getByName("main").java.srcDirs)
        }

        afterEvaluate {
            with(publishing) {
                publications {
                    create<MavenPublication>("release") {
                        from(components["release"])
                        artifact(sourcesJar)
                        this.groupId = groupId
                        this.artifactId = artifactId
                        this.version = version
                    }
                }
            }
        }
    }
}

fun Project.applyDependencies() {
    dependencies {
        myDependencies()
        myTestsDependencies()
    }
}

