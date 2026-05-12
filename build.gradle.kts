plugins {
    id("java")
}

group = "com.loris.jncore"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.fazecast:jSerialComm:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}