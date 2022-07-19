import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	//base plugins from initializer
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.bestester"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

val exposedVersion: String by project
dependencies {
	//base dependencies from the initializer
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//dependencies from previous project:
	//exposed imports
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
	//or    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
	//sql config
	implementation("mysql:mysql-connector-java:8.0.27")
	//hikari config
//	implementation( files("C:\\Users\\patlo\\Desktop\\Development\\jars\\HikariCP-5.0.0.jar"))
	// https://mvnrepository.com/artifact/com.zaxxer/HikariCP
	implementation("com.zaxxer:HikariCP:2.3.2")
	//log4j
	implementation("org.slf4j:slf4j-nop:1.7.30")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
