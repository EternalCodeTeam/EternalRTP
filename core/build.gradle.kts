dependencies {
    implementation("dev.rollczi.litecommands:core:1.9.0")
    implementation("net.dzikoysk:cdn:1.13.20")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}