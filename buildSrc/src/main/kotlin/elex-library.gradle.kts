plugins {
	id("elex-base")
	`java-library`
	`maven-publish`
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
			pom {
				name.set(project.name)
				description.set(project.description)
				url.set("https://www.elex-project.com/")
				organization {
					name.set("Elex co.,Pte.")
					url.set("https://www.elex-project.com/")
				}
				licenses {
					license {
						name.set("BSD 3-Clause License")
						url.set("https://github.com/elex-project/goblin/blob/master/LICENSE")
					}
				}
				developers {
					developer {
						id.set("elex")
						name.set("Elex")
						url.set("https://www.elex.pe.kr/")
						email.set("developer@elex-project.com")
						organization.set("Elex Co.,Pte.")
						organizationUrl.set("https://www.elex-project.com/")
						roles.set(arrayListOf("Developer", "CEO"))
						timezone.set("Asia/Seoul")
					}
				}
				scm {
					connection.set("scm:git:https://github.com/elex-project/goblin.git")
					developerConnection.set("scm:git:https://github.com/elex-project/goblin.git")
					url.set("https://github.com/elex-project/goblin")
				}
			}
		}
	}

	repositories {
		maven {
			name = "mavenElex"
			val urlRelease = uri("https://repository.elex-project.com/repository/maven-releases")
			val urlSnapshot = uri("https://repository.elex-project.com/repository/maven-snapshots")
			url = if (version.toString().endsWith("SNAPSHOT")) urlSnapshot else urlRelease
			// Repository credential, Must be defined in ~/.gradle/gradle.properties
			credentials {
				username = project.findProperty("repo.username") as String
				password = project.findProperty("repo.password") as String
			}
		}
		maven {
			name = "mavenGithub"
			url = uri("https://maven.pkg.github.com/elex-project/goblin")
			credentials {
				username = project.findProperty("github.username") as String
				password = project.findProperty("github.token") as String
			}
		}
	}
}

dependencies {

}
