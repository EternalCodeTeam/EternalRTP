import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.eternalcode.java-conventions")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("xyz.jpenilla.run-paper") version "2.0.1"
}

dependencies {
    implementation(project(":core"))

    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    implementation("io.papermc:paperlib:1.0.8")
    implementation("dev.rollczi.litecommands:bukkit:2.8.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
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

tasks.withType<ShadowJar> {
    archiveFileName.set("EternalRTP v${project.version} (MC 1.8.8-1.18).jar")

    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "javax/**",
        "META-INF/**"
    )

    mergeServiceFiles()
    minimize()

    val prefix = "com.eternalcode.randomtp.libs"
    listOf(
        "net.dzikoysk",
        "dev.rollczi",
        "org.panda_lang",
        "panda",
        "io.papermc.lib"
    ).forEach { pack ->
        relocate(pack, "$prefix.$pack")
    }
}

tasks {
    runServer {
        minecraftVersion("1.19.3")
    }
}
