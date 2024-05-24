plugins {
    id("jvm-conventions")
    `maven-publish`
}

//tasks {
//    publishing {
//        publications {
//            create<MavenPublication>("shadow") {
//                artifact(this@tasks["shadowJar"]) {
//                    classifier = null
//                }
//
//                artifactId = "${rootProject.name}-${rootProject.version}"
//
//                repositories {
//                    maven("https://maven.pkg.github.com/AmmoniumStudios/smple-sdk") {
//                        credentials {
//                            username = System.getenv("USERNAME")
//                            password = System.getenv("GITHUB_TOKEN")
//                        }
//
//                    }
//                }
//            }
//        }
//    }
//}