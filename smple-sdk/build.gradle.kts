plugins {
    id("library-conventions")
}

dependencies {
    compileOnly(libs.paper)
    api(libs.cloud)
    api(libs.cloud.annotations)
    api(libs.caffeine)
}

