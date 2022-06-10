import extensions.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author RubioAlves
 * Created 01/05/2022 at 12:27
 */
class ExtensionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins()
            applyAndroid()
            applyDependencies()
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            material()
            kotlin()
            lifecycle()
            viewBinding()
            shimmer()
        }
    }
}