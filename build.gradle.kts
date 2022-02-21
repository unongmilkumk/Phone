plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"

}
group = "io.github.jesecci"
version = "0.1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

project.extra.set("packageName", name.replace("-", ""))
project.extra.set("pluginName", name.split('-').joinToString("") { it.capitalize() })

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public/")
    maven (url= "https://repo.dmulloy2.net/repository/public/" )
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot:1.18.1-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")
    //compileOnly("io.papermc.paper:paper:1.18-R0.1-SNAPSHOT")

    implementation(kotlin("stdlib"))
//    implementation("io.github.monun:tap-api:4.1.3")
    implementation("io.github.monun:kommand-api:2.8.0")
    compileOnly ("com.comphenix.protocol", "ProtocolLib", "4.7.0")
    //implementation(files("/src/main/resources/ArmorEquipEvent-1.7.2.jar"))
    //implementation(files("/src/main/resources/Kotlin-1.6.0-all.jar"))
    //testImplementation(files("/src/main/resources/ArmorEquipEvent-1.7.2.jar"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.mockito:mockito-core:4.3.1")
}

tasks {
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
            expand(project.extra.properties)
        }
    }

    test {
        useJUnitPlatform()
    }

    create<Jar>("paperJar") {
        from(sourceSets["main"].output)
        archiveBaseName.set(project.extra.properties["pluginName"].toString())
        archiveVersion.set("") // For bukkit plugin update

        doLast {
            copy {
                from(archiveFile)
                val plugins = File(rootDir, ".debug/plugins/")
                into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
            }
        }
    }
}