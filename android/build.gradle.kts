allprojects {
    repositories {
        google()
        mavenCentral()
    }
    repositories{
        val storageUrl = System.getenv("FLUTTER_STORAGE_BASE_URL") ?: "https://storage.googleapis.com"
        repositories {
            google()
            mavenCentral()
            maven {
//                setUrl("../lib/build/host/outputs/repo")
                setUrl("$rootDir/app/libs/repo")
            }
            maven {
                setUrl("$storageUrl/download.flutter.io")
            }
            maven { setUrl("https://plugins.gradle.org/m2/") }
        }
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
