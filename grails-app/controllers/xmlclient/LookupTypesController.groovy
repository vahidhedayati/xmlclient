package xmlclient



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.xml.XmlUtil

@Transactional(readOnly = true)
class LookupTypesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LookupTypes.list(params), model:[lookupTypesInstanceCount: LookupTypes.count()]
    }

    def show(LookupTypes lookupTypesInstance) {
		
		
		try {
			lookupTypesInstance.content = XmlUtil.serialize(lookupTypesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
		
        respond lookupTypesInstance
    }

    def create() {
        respond new LookupTypes(params)
    }

    @Transactional
    def save(LookupTypes lookupTypesInstance) {
        if (lookupTypesInstance == null) {
            notFound()
            return
        }
		
		def xmloutput
		try {
			lookupTypesInstance.content=XmlUtil.serialize(lookupTypesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}

        if (lookupTypesInstance.hasErrors()) {
            respond lookupTypesInstance.errors, view:'create'
            return
        }

		
        lookupTypesInstance.save flush:true

		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(lookupTypesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lookupTypes.label', default: 'LookupTypes'), lookupTypesInstance.id])
                redirect lookupTypesInstance
            }
            '*' { respond lookupTypesInstance, [status: CREATED] }
        }
    }

    def edit(LookupTypes lookupTypesInstance) {
        respond lookupTypesInstance
    }

    @Transactional
    def update(LookupTypes lookupTypesInstance) {
        if (lookupTypesInstance == null) {
            notFound()
            return
        }
		def xmloutput
		try {
			lookupTypesInstance.content=XmlUtil.serialize(lookupTypesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
        if (lookupTypesInstance.hasErrors()) {
            respond lookupTypesInstance.errors, view:'edit'
            return
        }

		
        lookupTypesInstance.save flush:true
		
		
		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(lookupTypesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'LookupTypes.label', default: 'LookupTypes'), lookupTypesInstance.id])
                redirect lookupTypesInstance
            }
            '*'{ respond lookupTypesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(LookupTypes lookupTypesInstance) {

        if (lookupTypesInstance == null) {
            notFound()
            return
        }

        lookupTypesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'LookupTypes.label', default: 'LookupTypes'), lookupTypesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupTypes.label', default: 'LookupTypes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
