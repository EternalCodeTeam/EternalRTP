import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    checkstyle

    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "com.eternalcode"
version = "1.0.0"

checkstyle {
    toolVersion = "10.12.1"

    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")

    maxErrors = 0
    maxWarnings = 0
}

repositories {
    mavenCentral()
    gradlePluginPortal()

    maven { url = uri("https://repo.eternalcode.pl/releases") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.panda-lang.org/releases") }
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    // Spigot api
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")

    // kyori
    implementation("net.kyori:adventure-platform-bukkit:4.3.0")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")

    // litecommands
    implementation("dev.rollczi.litecommands:bukkit-adventure:2.8.8")

    // cdn configs
    implementation("net.dzikoysk:cdn:1.14.4")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileJava {
        options.compilerArgs = listOf("-Xlint:deprecation", "-parameters")
        options.encoding = "UTF-8"
    }

    runServer {
        minecraftVersion("1.19.4")
    }
    // run local developement server ^^^

    bukkit {
        main = "com.eternalcode.randomteleport.RandomTeleport"
        author = "EternalCodeTeam"
        apiVersion = "1.13"
        prefix = "EternalRTP"
        name = "EternalRTP"
        version = "${project.version}"
    }
    // generate plugin.yml ^^^

    shadowJar {
        archiveFileName.set("EternalRTP v${project.version}.jar")

        dependsOn("checkstyleMain")

        exclude(
            "META-INF/**",
            "org/intellij/lang/annotations/**",
            "org/jetbrains/annotations/**",
        )

        val prefix = "com.eternalcode.randomteleport.libs"
        listOf(
            "dev.rollczi.litecommands",
            "panda.std",
            "net.kyori",
            "net.dzikoysk",
        ).forEach { relocate(it, prefix) }
    }
}
