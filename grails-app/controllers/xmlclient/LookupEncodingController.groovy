package xmlclient



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.xml.XmlUtil

@Transactional(readOnly = true)
class LookupEncodingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LookupEncoding.list(params), model:[lookupEncodingInstanceCount: LookupEncoding.count()]
    }

    def show(LookupEncoding lookupEncodingInstance) {
		
		
		try {
			lookupEncodingInstance.content = XmlUtil.serialize(lookupEncodingInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
		
        respond lookupEncodingInstance
    }

    def create() {
        respond new LookupEncoding(params)
    }

    @Transactional
    def save(LookupEncoding lookupEncodingInstance) {
        if (lookupEncodingInstance == null) {
            notFound()
            return
        }
		
		def xmloutput
		try {
			lookupEncodingInstance.content=XmlUtil.serialize(lookupEncodingInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}

        if (lookupEncodingInstance.hasErrors()) {
            respond lookupEncodingInstance.errors, view:'create'
            return
        }

		
        lookupEncodingInstance.save flush:true

		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(lookupEncodingInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lookupEncoding.label', default: 'LookupEncoding'), lookupEncodingInstance.id])
                redirect lookupEncodingInstance
            }
            '*' { respond lookupEncodingInstance, [status: CREATED] }
        }
    }

    def edit(LookupEncoding lookupEncodingInstance) {
        respond lookupEncodingInstance
    }

    @Transactional
    def update(LookupEncoding lookupEncodingInstance) {
        if (lookupEncodingInstance == null) {
            notFound()
            return
        }
		def xmloutput
		try {
			lookupEncodingInstance.content=XmlUtil.serialize(lookupEncodingInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
        if (lookupEncodingInstance.hasErrors()) {
            respond lookupEncodingInstance.errors, view:'edit'
            return
        }

		
        lookupEncodingInstance.save flush:true
		
		
		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(lookupEncodingInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'LookupEncoding.label', default: 'LookupEncoding'), lookupEncodingInstance.id])
                redirect lookupEncodingInstance
            }
            '*'{ respond lookupEncodingInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(LookupEncoding lookupEncodingInstance) {

        if (lookupEncodingInstance == null) {
            notFound()
            return
        }

        lookupEncodingInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'LookupEncoding.label', default: 'LookupEncoding'), lookupEncodingInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupEncoding.label', default: 'LookupEncoding'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
