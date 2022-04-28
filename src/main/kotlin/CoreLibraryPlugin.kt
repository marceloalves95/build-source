import extensions.applyAndroid
import extensions.applyDependencies
import extensions.applyPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author RubioAlves
 * Created 24/04/2022 at 13:38
 */
class CoreLibraryPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project){
            applyPlugins()
            applyAndroid()
            applyDependencies()
        }
    }
}