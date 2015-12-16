package xmlclient



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.xml.XmlUtil

@Transactional(readOnly = true)
class SendTypesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SendTypes.list(params), model:[sendTypesInstanceCount: SendTypes.count()]
    }

    def show(SendTypes sendTypesInstance) {
		
		
		try {
			sendTypesInstance.content = XmlUtil.serialize(sendTypesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
		
        respond sendTypesInstance
    }

    def create() {
        respond new SendTypes(params)
    }

    @Transactional
    def save(SendTypes sendTypesInstance) {
        if (sendTypesInstance == null) {
            notFound()
            return
        }
		
		def xmloutput
		try {
			sendTypesInstance.content=XmlUtil.serialize(sendTypesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}

        if (sendTypesInstance.hasErrors()) {
            respond sendTypesInstance.errors, view:'create'
            return
        }

		
        sendTypesInstance.save flush:true

		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(sendTypesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sendTypes.label', default: 'SendTypes'), sendTypesInstance.id])
                redirect sendTypesInstance
            }
            '*' { respond sendTypesInstance, [status: CREATED] }
        }
    }

    def edit(SendTypes sendTypesInstance) {
        respond sendTypesInstance
    }

    @Transactional
    def update(SendTypes sendTypesInstance) {
        if (sendTypesInstance == null) {
            notFound()
            return
        }
		def xmloutput
		try {
			sendTypesInstance.content=XmlUtil.serialize(sendTypesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
        if (sendTypesInstance.hasErrors()) {
            respond sendTypesInstance.errors, view:'edit'
            return
        }

		
        sendTypesInstance.save flush:true
		
		
		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(sendTypesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SendTypes.label', default: 'SendTypes'), sendTypesInstance.id])
                redirect sendTypesInstance
            }
            '*'{ respond sendTypesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SendTypes sendTypesInstance) {

        if (sendTypesInstance == null) {
            notFound()
            return
        }

        sendTypesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SendTypes.label', default: 'SendTypes'), sendTypesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendTypes.label', default: 'SendTypes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
