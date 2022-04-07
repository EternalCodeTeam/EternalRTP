dependencies {
    implementation(project(":core"))

    implementation("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("io.papermc:paperlib:1.0.7")
    implementation("dev.rollczi.litecommands:bukkit:1.9.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}