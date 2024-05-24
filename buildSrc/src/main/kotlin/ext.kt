import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

fun ShadowJar.reloc(vararg paths: String) {
    paths.forEach { relocate(it, "org.ammonium.smple.sdk.$it") }
}

