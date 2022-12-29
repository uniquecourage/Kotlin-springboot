import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.21"
	kotlin("plugin.spring") version "1.7.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	implementation("org.mybatis:mybatis:3.5.11")
	implementation("org.ktorm:ktorm-core:3.5.0")
	implementation("org.ktorm:ktorm-support-postgresql:3.5.0")
//	ktor
	implementation("io.ktor:ktor-server-core:2.2.1")
	implementation("io.ktor:ktor-serialization:2.2.1")
	implementation("io.ktor:ktor-server-netty:2.2.1")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
	testImplementation("io.ktor:ktor-server-tests:2.2.1")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.6.10")
	runtimeOnly("org.postgresql:postgresql")
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
