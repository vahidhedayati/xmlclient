grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {
		runtime('org.codehaus.groovy.modules.http-builder:http-builder:0.5.1') {
			excludes 'xalan'
			excludes 'xml-apis'
			excludes 'groovy'
		}
		
		compile "dnsjava:dnsjava:2.0.8"
	}

	plugins {
		//runtime ":hibernate4:4.3.6.1", {
		//	export = false
		//}
		//runtime ":hibernate:3.6.10.10", { 
		//	export = false
		//}
	
		build ':release:3.0.1', ':rest-client-builder:2.0.3', {
			export = false
		}
	}
}
