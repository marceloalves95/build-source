import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.repositories

/**
 * @author RubioAlves
 * Created 24/04/2022 at 08:47
 */
class ProjectPlugin: Plugin<Project> {
    override fun apply(project: Project){
        with(project) {
            allprojects {
                repositories {
                    google()
                    mavenCentral()
                }
            }

            tasks.register("clean", Delete::class) {
                delete("includedBuilds/build-configuration")
            }

        }
    }
}