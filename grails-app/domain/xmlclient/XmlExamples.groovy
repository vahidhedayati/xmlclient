package xmlclient

class XmlExamples {
	String name
	String content
	
	static belongsTo=[XmlUrls]

	static mapping= {
		content type: 'text'
	}
	
	String toString() { 
		"${name}"
	}
	
}
