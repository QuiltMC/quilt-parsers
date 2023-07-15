plugins {
    id("org.quiltmc.gradle.licenser") version "2.+"
    id("maven-publish")
    id("java-library")
    id("signing")
}

group = "org.quiltmc"
version = "0.2.1"

repositories {
    mavenCentral()
}

val env = System.getenv()

subprojects {
    apply(plugin="org.quiltmc.gradle.licenser")
    apply(plugin="java-library")
    apply(plugin="maven-publish")
    apply(plugin="signing")

    java {
        withSourcesJar()
        withJavadocJar()

        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    license {
        rule(rootProject.file("codeformat/GSON_MODIFIED_HEADER"))
        rule(rootProject.file("codeformat/HEADER"))
    }

    tasks.withType(Javadoc::class) {
        isFailOnError = false
        options.encoding = "UTF-8"
    }

    publishing {
        publications {
            create("mavenJava", MavenPublication::class) {
                pom {
                    name.set("parsers ${project.displayName.uppercase()}") // TODO: read a property with a real name
                    packaging = "jar"
                    description.set(project.description)
                    url.set("https://github.com/QuiltMC/parsers")

                    scm {
                        connection.set("scm:git:https://github.com/QuiltMC/parsers.git")
                        developerConnection.set("scm:git:ssh:git@github.com:QuiltMC/parsers.git")
                        url.set("https://github.com/QuiltMC/parsers")
                    }

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("quiltmc")
                            name.set("Quilt Loader Team")
                            email.set("infrastructure@gmail.com")
                        }
                    }

                    from(components["java"])
                }
            }
        }
        repositories {
            env["MAVEN_URL"]?.let {
                repositories.maven {
                    url = uri(it)

                    credentials {
                        username = env["MAVEN_USERNAME"]!!
                        password = env["MAVEN_PASSWORD"]!!
                    }
                }
            } ?: run {
                mavenLocal()
            }
        }
    }

    signing {
        val key = env["SIGNING_KEY"]
        val pass = env["SIGNING_KEY_PASSPHRASE"]
        if (key != null && pass != null && key != "" && pass != "") {
            useInMemoryPgpKeys(key, pass)
            sign(publishing.publications["mavenJava"])
        }
    }
}