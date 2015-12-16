package xmlclient

class XmlUrls {
	
	String name
	String url
	
	static hasMany=[examples: XmlExamples]
	
	static mapping= {
		examples cascade: 'lock'
	}
	
	String toString() {
		"${name}"
	}
	
}
