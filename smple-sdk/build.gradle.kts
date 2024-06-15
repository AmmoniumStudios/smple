plugins {
    id("library-conventions")
    id("io.freefair.lombok") version "8.6"
}

dependencies {
    compileOnly(libs.paper)
    api(libs.cloud)
    api(libs.cloud.annotations)
    api(libs.configurate)
    api(libs.postgresql)
    api(libs.hikari)
}

