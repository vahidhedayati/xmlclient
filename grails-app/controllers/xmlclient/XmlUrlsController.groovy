package xmlclient



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Transactional(readOnly = true)
class XmlUrlsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond XmlUrls.list(params), model:[xmlUrlsInstanceCount: XmlUrls.count()]
    }

    def show(XmlUrls xmlUrlsInstance) {
        respond xmlUrlsInstance
    }

    def create() {
        respond new XmlUrls(params)
    }

    @Transactional
    def save(XmlUrls xmlUrlsInstance) {
        if (xmlUrlsInstance == null) {
            notFound()
            return
        }

        if (xmlUrlsInstance.hasErrors()) {
            respond xmlUrlsInstance.errors, view:'create'
            return
        }

        xmlUrlsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'xmlUrls.label', default: 'XmlUrls'), xmlUrlsInstance.id])
                redirect xmlUrlsInstance
            }
            '*' { respond xmlUrlsInstance, [status: CREATED] }
        }
    }

    def edit(XmlUrls xmlUrlsInstance) {
        respond xmlUrlsInstance
    }

    @Transactional
    def update(XmlUrls xmlUrlsInstance) {
        if (xmlUrlsInstance == null) {
            notFound()
            return
        }

        if (xmlUrlsInstance.hasErrors()) {
            respond xmlUrlsInstance.errors, view:'edit'
            return
        }

        xmlUrlsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'XmlUrls.label', default: 'XmlUrls'), xmlUrlsInstance.id])
                redirect xmlUrlsInstance
            }
            '*'{ respond xmlUrlsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(XmlUrls xmlUrlsInstance) {

        if (xmlUrlsInstance == null) {
            notFound()
            return
        }

        xmlUrlsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'XmlUrls.label', default: 'XmlUrls'), xmlUrlsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xmlUrls.label', default: 'XmlUrls'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
