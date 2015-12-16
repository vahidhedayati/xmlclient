package xmlclient


import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import groovy.xml.XmlUtil

import org.apache.http.HttpStatus


class UtilsController  {

	def xmlUtilService

	def index() {}
	def testxml() {}
	def advanced() {}
	def admin() {}
	
	def parsexml(String input) {
		input=input.replaceAll('[\\n|\\r]','')
		def output
		try {
			output=XmlUtil.serialize(input)
		}catch (Exception e) {
			flash.message = message(code:'Invalid XML Request')
			output=e
		}
		[input:output]
	}

	def exampleContent() {
		def cid=params.id
		def cn=XmlExamples.findByName(cid)
		if (cn) {
			render cn.content as String
		}
	}

	def listExamples() {
		def cid=params.id
		if (cid){
			def xmlus=XmlUrls.findByName(cid)
			if (xmlus) {
				[ xmlus: xmlus ]
			}
		}
	}

	def processxml (String xmlurl, String xmlinput, String xmltype, String encoding, String rtype) {
		Map model
		xmlinput=xmlinput.replaceAll('[\\n|\\r]','')
		def xmloutput
		def xmlerr
		if (rtype=="text/xml") {
			try {
				xmloutput=XmlUtil.serialize(xmlinput)
			}catch (Exception e) {
				flash.message = message(code:'Invalid XML Request')
				xmlerr=e
			}
		}else{
			xmloutput=xmlinput
		}

		if (xmloutput) {
			if (!xmltype) {
				xmltype="POST"
			}

			if (!encoding) {
				encoding="UTF8"
			}

			if (!xmlurl) {
				xmlurl="https://host.that.accepts.xml/"
			}


			/*
			 def url = new URL(xmlurl)
			 String xmlauthority = url.authority
			 String xmlpath = url.path
			 String xmlprotocol = url.protocol
			 String xmlserver = url.host
			 String conurl = xmlprotocol + '://' + xmlauthority
			 */

			def result=processRequest(xmltype, xmlinput, xmlurl, encoding, rtype)
			def good=result?.good
			def output=result?.output
			def status=result?.status
			def errors=result?.errors
			def resHeader = result?.resHeader
			if (output) {
				def rmap
				if (good) {
					if (rtype=="xml") {
						rmap=xmlParser(output)
						output = XmlUtil.serialize(output as String)
					}
					model= [output:output,status:status, url:xmlurl, rmap:rmap, resHeader:resHeader]
				}else{
					model = [output:output as String,status:status, url:xmlurl, rmap:rmap]
				}

			}else{
				model = [output:status+"\n"+errors,status:status, url:xmlurl]
			}

		}else{
			model = [output:xmlerr,url:xmlurl,status:'No Attempt made - Invalid XML Input']
		}
		render view:'processxml', model: model
	}

	private ArrayList xmlParser(String xml) {
		def records = new XmlParser().parseText(xml)
		def rmap=[]
		records.depthFirst().eachWithIndex { it, index ->
			rmap+= [nid : index,  name : it.name(), attribute : it.attributes(), val : it.value()]
		}
		return rmap
	}



	private Map processRequest(String xmltype,  String xmlinput, String url, String encoding, String rtype) {
		String output,status,errors=''
		int statusCode
		boolean good = false
		Map res  = xmlUtilService.sendHttp(url, xmlinput, xmltype, encoding, rtype)
		status = res.cstat
		def clen = res.clen
		def chunk = res.chunk
		def cenc = res.cenc
		output = res.ccont
		statusCode = res.statusCode as Integer
		def resHeader = res.resHeader
		if (HttpStatus.SC_OK == statusCode) {
			good = true
		}
		return [output:output,good:good,status:status,errors:errors, resHeader:resHeader]
	}
}