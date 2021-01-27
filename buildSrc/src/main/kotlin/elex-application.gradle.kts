
plugins {
	id("elex-base")
	application
}

tasks.jar {
	manifest {
		attributes(mapOf(
			"Implementation-Title" to project.name,
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to "ELEX co.,pte.",
			"Main-Class" to application.mainClass,
			"Automatic-Module-Name" to "com.elex_project.${project.name}"
		))
	}
}
