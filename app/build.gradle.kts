plugins {
    id("elex-application")
}
application{
    mainClass.set("com.elex_project.goblin.Application")
}
dependencies {
    implementation(project(":geom-2d"))

}
