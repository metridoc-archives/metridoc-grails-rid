grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

//location of the release repository
grails.project.repos.metridocRepo.url = "https://api.bintray.com/maven/upennlib/metridoc/metridoc-rid"
//name of the repository
grails.project.repos.default = "metridocRepo"

grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits("global")
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo "http://metridoc.googlecode.com/svn/maven/repository/"
        mavenRepo "http://dl.bintray.com/upennlib/metridoc"
        mavenRepo "http://dl.bintray.com/upennlib/maven"
    }
    dependencies {
        compile("org.apache.poi:poi:3.8-beta3")
        compile("org.apache.poi:poi-ooxml:3.8-beta3") {
            excludes 'poi'
            excludes 'dom4j'
        }

    }

    plugins {
        build(":rest-client-builder:1.0.3")
        compile(":metridoc-core:0.7.3")
        //TODO: Remove when we upgrade metridoc core
        compile ":hibernate:$grailsVersion"
        compile ":webxml:1.4.1"
        build(":release:2.2.1", ":bintray-upload:0.2", ":tomcat:$grailsVersion") {
            export = false
        }
        runtime ":jquery:1.10.0"
    }
}
