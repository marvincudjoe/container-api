import org.gradle.api.JavaVersion.VERSION_20
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    kotlin("plugin.jpa") version "1.9.10"
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
    const val SPRING_VERSION = "3.1.4"
    const val SPRING_DOC_VERSION = "2.2.0"
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

    implementation("org.springdoc:springdoc-openapi-starter-common:${Versions.SPRING_DOC_VERSION}")

    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_VERSION}")
    // https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools:${Versions.SPRING_VERSION}")

    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    implementation("com.github.docker-java:docker-java:${Versions.DOCKER_JAVA_VERSION}")
    implementation("com.github.docker-java:docker-java-transport-httpclient5:${Versions.DOCKER_JAVA_VERSION}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_VERSION}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        // Enable strict null checks https://kotlinlang.org/docs/java-interop.html#jsr-305-support
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = javaVersion.toString()
    }
}

tasks.test {
    useJUnitPlatform()
}
