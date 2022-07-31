plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

dependencies {
    implementation(project(":core"))

    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("io.papermc:paperlib:1.0.7")
    implementation("dev.rollczi.litecommands:bukkit:1.9.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
}

bukkit {
    main = "com.eternalcode.randomtp.bukkit.PluginEternalRandomTp"
    apiVersion = "1.13"
    author = "Rollczi"
    name = "EternalRandomTeleport"
    version = "${project.version}"
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("EternalRTP v${project.version} (MC 1.8.8-1.18).jar")

    exclude("org/intellij/lang/annotations/**")
    exclude("org/jetbrains/annotations/**")
    exclude("javax/**")
    exclude("META-INF/**")

    relocate("net.dzikoysk", "com.eternalcode.randomtp.libs.net.dzikoysk")
    relocate("dev.rollczi", "com.eternalcode.randomtp.libs.dev.rollczi")
    relocate("org.panda_lang", "com.eternalcode.randomtp.libs.org.panda_lang")
    relocate("panda", "com.eternalcode.randomtp.libs.panda")
    relocate("io.papermc.lib", "com.eternalcode.randomtp.libs.io.papermc.lib")
}
