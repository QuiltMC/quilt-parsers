plugins {
    id("java-library")
    id("maven-publish")
}

group = rootProject.group.toString() + "." + rootProject.name
version = rootProject.version
description = "A simple reader and writer API for JSON, JSONC, and JSON5, based on GSON."

repositories {
    mavenCentral()
}

tasks.compileJava {
    options.release.set(8)
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
tasks.jar {
    manifest {
        attributes(Pair("Automatic-Module-Name", "org.quiltmc.parsers.json"))
    }
}
dependencies {
    implementation("org.jetbrains:annotations:24.0.1")
}
