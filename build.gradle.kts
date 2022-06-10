import extensions.gradleDependencies

plugins {
    `kotlin-dsl`
    id("core-library-gradle-plugin")
}

repositories {
    google()
    mavenCentral()
}

dependencies{
    gradleDependencies()
}

gradlePlugin {
    plugins {
        register("core-project-plugin") {
            id = "core-project-plugin"
            implementationClass = "ProjectPlugin"
        }
        register("core-library-plugin") {
            id = "core-library-plugin"
            implementationClass = "CoreLibraryPlugin"
        }
        register("components-library-plugin") {
            id = "components-library-plugin"
            implementationClass = "ComponentsPlugin"
        }
        register("testing-library-plugin") {
            id = "testing-library-plugin"
            implementationClass = "TestingPlugin"
        }
        register("compose-library-plugin") {
            id = "compose-library-plugin"
            implementationClass = "ComposePlugin"
        }
        register("extensions-library-plugin") {
            id = "extensions-library-plugin"
            implementationClass = "ExtensionsPlugin"
        }
    }
}