plugins {
    java
    scala
    id("org.jetbrains.intellij") version "1.5.2"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/repositories/central/")
        isAllowInsecureProtocol = true
    }
    mavenCentral()
}

//scala {
//    zincVersion.set("1.2.0")
//}
sourceSets {
    main {
        scala {
            setSrcDirs(listOf("src/main/scala", "src/main/java"))
        }
        java {
            setSrcDirs(emptyList<String>())
        }
    }
}
//sourceSets {
//
//    main {
//        withConvention(ScalaSourceSet::class) {
//            scala {
//                setSrcDirs(listOf("src/main/scala", "src/main/java"))
//            }
//        }
//        java {
//            setSrcDirs(emptyList<String>())
//        }
//    }
//    test {
//        withConvention(ScalaSourceSet::class) {
//            scala {
//                setSrcDirs(listOf("src/test/scala", "src/test/java"))
//            }
//        }
//        java {
//            setSrcDirs(emptyList<String>())
//        }
//    }
//}


// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

dependencies {
    implementation("org.scala-lang:scala-library:2.11.12")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }


    patchPluginXml {
        sinceBuild.set("212")
        untilBuild.set("222.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
