package xmlclient



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.xml.XmlUtil

@Transactional(readOnly = true)
class DnsOverridesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DnsOverrides.list(params), model:[dnsOverridesInstanceCount: DnsOverrides.count()]
    }

    def show(DnsOverrides dnsOverridesInstance) {
		
		
		try {
			dnsOverridesInstance.content = XmlUtil.serialize(dnsOverridesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
		
        respond dnsOverridesInstance
    }

    def create() {
        respond new DnsOverrides(params)
    }

    @Transactional
    def save(DnsOverrides dnsOverridesInstance) {
        if (dnsOverridesInstance == null) {
            notFound()
            return
        }
		
		def xmloutput
		try {
			dnsOverridesInstance.content=XmlUtil.serialize(dnsOverridesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}

        if (dnsOverridesInstance.hasErrors()) {
            respond dnsOverridesInstance.errors, view:'create'
            return
        }

		
        dnsOverridesInstance.save flush:true

		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(dnsOverridesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dnsOverrides.label', default: 'DnsOverrides'), dnsOverridesInstance.id])
                redirect dnsOverridesInstance
            }
            '*' { respond dnsOverridesInstance, [status: CREATED] }
        }
    }

    def edit(DnsOverrides dnsOverridesInstance) {
        respond dnsOverridesInstance
    }

    @Transactional
    def update(DnsOverrides dnsOverridesInstance) {
        if (dnsOverridesInstance == null) {
            notFound()
            return
        }
		def xmloutput
		try {
			dnsOverridesInstance.content=XmlUtil.serialize(dnsOverridesInstance.content)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			//xmloutput=e
		}
        if (dnsOverridesInstance.hasErrors()) {
            respond dnsOverridesInstance.errors, view:'edit'
            return
        }

		
        dnsOverridesInstance.save flush:true
		
		
		String xmlUrl = params.xmlUrls
		XmlUrls xmlu
		if (xmlUrl) {
			println "WE HAVE ${xmlUrl}"
		  xmlu = XmlUrls.get(xmlUrl)
		  
		}
		if (xmlu) {
		xmlu.addToExamples(dnsOverridesInstance)
		xmlu.save(flush:true)
		println "WE HAVE UPDATED URL"
		}
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DnsOverrides.label', default: 'DnsOverrides'), dnsOverridesInstance.id])
                redirect dnsOverridesInstance
            }
            '*'{ respond dnsOverridesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DnsOverrides dnsOverridesInstance) {

        if (dnsOverridesInstance == null) {
            notFound()
            return
        }

        dnsOverridesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DnsOverrides.label', default: 'DnsOverrides'), dnsOverridesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dnsOverrides.label', default: 'DnsOverrides'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
