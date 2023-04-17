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

dependencies {
    implementation("org.jetbrains:annotations:24.0.1")
}
