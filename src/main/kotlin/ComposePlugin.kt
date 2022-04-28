import extensions.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author RubioAlves
 * Created 27/04/2022 at 07:19
 */
class ComposePlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins()
            applyAndroid()
            applyDependencies()
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            compose()
            composeTest()
        }
    }
}