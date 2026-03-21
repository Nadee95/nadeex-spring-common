plugins {
    `java-library`
    `maven-publish`
    id("io.spring.dependency-management") version "1.1.4"
    jacoco
}

group = "com.nadeex.spring"
version = "0.1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.3")
    }
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    api("com.fasterxml.jackson.core:jackson-databind")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> { useJUnitPlatform() }

tasks.test { finalizedBy(tasks.jacocoTestReport) }

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports { xml.required.set(true); html.required.set(true) }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.nadeex.spring"
            artifactId = "common"
            version = "0.1.0"
            from(components["java"])

            // Resolve and write actual versions into published POM
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }}
    }
    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Nadee95/nadeex-spring-common")
            credentials {
                username =  System.getenv("GITHUB_ACTOR")
                password =  System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
