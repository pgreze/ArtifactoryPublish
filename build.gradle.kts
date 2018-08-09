plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.2.60"
    id("com.novoda.bintray-release") version "0.8.0"
}

gradlePlugin {
    plugins {
        create("artifactorypublish") {
            id = "guru.stefma.artifactorypublish"
            implementationClass = "guru.stefma.artifactorypublish.ArtifactoryPublishPlugin"
        }
    }
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    // TODO: We run into this issue: https://github.com/JFrogDev/build-info/issues/122#issuecomment-308383928
    // Can't be resolved in a good way. Update me later ...
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:4.4.12") {
        exclude(module = "groovy-all")
    }
    implementation("guru.stefma.androidartifacts:androidartifacts:1.1.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testImplementation("org.assertj:assertj-core:3.10.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.artifactorypublish"
    artifactId = rootProject.name
    uploadName = "ArtifactoryPublish"
    version = "1.0.0"
    description = "Super duper easy way to release your Android and other artifacts to artifactory"
    website = "https://github.com/StefMa/ArtifactoryPublish"
    setLicences("MIT")
}
