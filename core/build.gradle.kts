plugins {
    id("com.eternalcode.java-conventions")
}

dependencies {
    implementation("dev.rollczi.litecommands:core:2.8.0")
    implementation("net.dzikoysk:cdn:1.14.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}