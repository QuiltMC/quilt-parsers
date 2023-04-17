plugins {
    id("java-library")
    id("maven-publish")
}

group = rootProject.group.toString() + "." + rootProject.name
version = rootProject.version
description = "An adapter of QUP's JSON readers and writers to GSON"
repositories {
    mavenCentral()
}

dependencies {
    api(project(":json"))
    api("com.google.code.gson:gson:2.10.1")
}
