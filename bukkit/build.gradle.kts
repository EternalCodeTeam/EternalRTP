import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

dependencies {
    implementation(project(":core"))

    compileOnly("org.spigotmc:spigot-api:1.19.1-R0.1-SNAPSHOT")
    implementation("io.papermc:paperlib:1.0.8")
    implementation("dev.rollczi.litecommands:bukkit:2.7.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
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
