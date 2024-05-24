plugins {
    `java-library`
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.ammonium.org/repositories/releases") {
        credentials {
            username = System.getenv("AMMONIUM_USER")
            password = System.getenv("AMMONIUM_PASSWORD")
        }
        authentication {
            create<BasicAuthentication>("basic")
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks {
    withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }

    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }
}

