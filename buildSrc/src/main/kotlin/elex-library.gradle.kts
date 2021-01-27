plugins {
	id ("elex-base")
	`java-library`
	`maven-publish`
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
			pom {
				// todo
				name.set(project.name)
				description.set(project.description)
				url.set("https://")
				properties.set(mapOf(
					"myProp" to "value",
					"prop.with.dots" to "anotherValue"
				))
				organization {
					name.set("Elex co.,Pte.")
					url.set("https://www.elex-project.com/")
				}
				licenses {
					license {
						// todo
						name.set("BSD 3-Clause License")
						url.set("licenseUrl")
						comments.set("")
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
						properties.set(mapOf("" to ""))
					}
				}
				contributors {
					contributor {
						name.set("")
						email.set("")
						url.set("")
					}
				}
				scm {
					// todo
					connection.set("scm:git:https://github.com/my-library.git")
					developerConnection.set("scm:git:https://github.com/my-library.git")
					url.set("https://github.com/my-library/")
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
		maven { //todo
			name = "mavenGithub"
			url = uri("https://maven.pkg.github.com/elex-project/tmpl-java-library")
			credentials {
				username = project.findProperty("github.username") as String
				password = project.findProperty("github.token") as String
			}
		}
	}
}

dependencies {

}
