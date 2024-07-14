plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
    signing
}

group = "io.github.ufukhalis.kstringsim"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "k-string-sim"
            groupId = "io.github.ufukhalis"
            version = "0.0.1"

            from(components["java"])

            pom {
                name.set("K-String Similarity")
                description.set("String similarity library for Kotlin")
                url.set("https://github.com/ufukhalis/k-string-sim")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/ufukhalis/k-string-sim/issues")
                }

                developers {
                    developer {
                        id.set("k-string-sim")
                        name.set("Ufuk Halis")
                        email.set("ufukhalis@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com:ufukhalis/k-string-sim.git")
                    developerConnection.set("scm:git:ssh://github.com:ufukhalis/k-string-sim.git")
                    url.set("https://github.com/ufukhalis/k-string-sim")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("sonatype.username")?.toString()
                password = findProperty("sonatype.password")?.toString()
            }
        }
    }
}


signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}
