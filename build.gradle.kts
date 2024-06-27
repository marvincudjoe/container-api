import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.dokka") version "1.9.10"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
}

val javaVersion = VERSION_17.majorVersion.toInt()
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
    const val KOTLIN_VERSION = "2.0.0"
    const val GOOGLE_GUAVA = "33.0.0-jre"
    const val DOCKER_JAVA_VERSION = "3.3.6"
    const val SPRING_VERSION = "3.3.1"
    const val SPRING_DOC_VERSION = "2.5.0"
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:${Versions.GOOGLE_GUAVA}")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.SPRING_DOC_VERSION}")
    implementation("org.springdoc:springdoc-openapi-starter-common:${Versions.SPRING_DOC_VERSION}")

    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_VERSION}")
    // https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools:${Versions.SPRING_VERSION}")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("com.github.docker-java:docker-java:${Versions.DOCKER_JAVA_VERSION}")
    implementation("com.github.docker-java:docker-java-transport-httpclient5:${Versions.DOCKER_JAVA_VERSION}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_VERSION}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        // Enable strict null checks https://kotlinlang.org/docs/java-interop.html#jsr-305-support
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget =
            VERSION_17.toString() // Move to 21 https://docs.gradle.org/current/userguide/compatibility.html#java
    }
}

tasks.test {
    useJUnitPlatform()
}
