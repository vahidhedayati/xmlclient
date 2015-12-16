package xmlclient



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.xml.XmlUtil

@Transactional(readOnly = true)
class XmlExamplesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond XmlExamples.list(params), model:[xmlExamplesInstanceCount: XmlExamples.count()]
    }

    def show(XmlExamples xmlExamplesInstance) {
		
		
		try {
			xmlExamplesInstance.content = XmlUtil.serialize(xmlExamplesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
		
        respond xmlExamplesInstance
    }

    def create() {
        respond new XmlExamples(params)
    }

    @Transactional
    def save(XmlExamples xmlExamplesInstance) {
        if (xmlExamplesInstance == null) {
            notFound()
            return
        }
		
		def xmloutput
		try {
			xmlExamplesInstance.content=XmlUtil.serialize(xmlExamplesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}

        if (xmlExamplesInstance.hasErrors()) {
            respond xmlExamplesInstance.errors, view:'create'
            return
        }

		
        xmlExamplesInstance.save flush:true

		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(xmlExamplesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'xmlExamples.label', default: 'XmlExamples'), xmlExamplesInstance.id])
                redirect xmlExamplesInstance
            }
            '*' { respond xmlExamplesInstance, [status: CREATED] }
        }
    }

    def edit(XmlExamples xmlExamplesInstance) {
        respond xmlExamplesInstance
    }

    @Transactional
    def update(XmlExamples xmlExamplesInstance) {
        if (xmlExamplesInstance == null) {
            notFound()
            return
        }
		def xmloutput
		try {
			xmlExamplesInstance.content=XmlUtil.serialize(xmlExamplesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
        if (xmlExamplesInstance.hasErrors()) {
            respond xmlExamplesInstance.errors, view:'edit'
            return
        }

		
        xmlExamplesInstance.save flush:true
		
		
		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(xmlExamplesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'XmlExamples.label', default: 'XmlExamples'), xmlExamplesInstance.id])
                redirect xmlExamplesInstance
            }
            '*'{ respond xmlExamplesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(XmlExamples xmlExamplesInstance) {

        if (xmlExamplesInstance == null) {
            notFound()
            return
        }

        xmlExamplesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'XmlExamples.label', default: 'XmlExamples'), xmlExamplesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xmlExamples.label', default: 'XmlExamples'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
