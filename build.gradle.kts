import org.gradle.api.JavaVersion.VERSION_20

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
}

val javaVersion = VERSION_20.majorVersion.toInt()
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

object Versions {
    const val KOTLIN_VERSION = "1.9.10"
    const val DOCKER_JAVA_VERSION = "3.3.3"
    const val UNIREST_VERSION = "3.14.2"
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

    implementation("com.github.docker-java:docker-java:${Versions.DOCKER_JAVA_VERSION}")
    implementation("com.github.docker-java:docker-java-transport-httpclient5:${Versions.DOCKER_JAVA_VERSION}")
    implementation("com.konghq:unirest-java:${Versions.UNIREST_VERSION}")
}

tasks.test {
    useJUnitPlatform()
}
