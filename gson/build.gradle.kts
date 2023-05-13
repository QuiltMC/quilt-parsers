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

tasks.compileJava {
    options.release.set(11)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(project(":json"))
    api("com.google.code.gson:gson:2.10.1")
}
