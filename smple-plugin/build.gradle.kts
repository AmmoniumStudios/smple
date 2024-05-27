import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("jvm-conventions")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
    id("io.freefair.lombok") version "8.6"
}

dependencies {
    implementation(project(":smple-sdk"))
    compileOnly(libs.paper)
}

tasks.withType<ShadowJar> {
    archiveFileName.set("Smple-${project.version}.jar")
}

bukkit {
    name = "Smple"
    main = "org.ammonium.smple.SmplePlugin"
    version = "0.0.0"
    description = "A simple plugin"
    author = "Ammonium Studios"
    apiVersion = "1.20"
}

tasks.build {
    dependsOn("shadowJar")
}