package xmlclient

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

class XmlUtilService  {


	def Map sendHttp(String httpUrl, String data,String mtype, String encoding, String rtype=null) {
		String cstat, clen, chunk, cenc, ccont
		int statusCode
		def resHeader
		HttpClient httpClient = new DefaultHttpClient()
		try {
			def httpPost
			if (mtype == "POST") {
				httpPost = new HttpPost(httpUrl)
			} else if (mtype == "GET") {
				httpPost = new HttpGet(httpUrl)
			} else if (mtype == "PUT") {
				httpPost = new HttpPut(httpUrl)
			}

			if (!rtype) {
				rtype = "text/xml"
			}

			httpPost.setHeader("Content-Type", rtype)
			HttpEntity reqEntity = new StringEntity(data, encoding)
			reqEntity.setContentType(rtype)
			reqEntity.setChunked(true)
			httpPost.setEntity(reqEntity)
			//println "SH 6"
			HttpResponse response = httpClient.execute(httpPost)
			HttpEntity resEntity = response.getEntity()
			resHeader = response.getAllHeaders()
			cstat = response.getStatusLine()
			statusCode = response.getStatusLine().getStatusCode()
			//println "SH 7"
			if (resEntity != null) {
				clen = resEntity.getContentLength()
				chunk = resEntity.isChunked()
				cenc = resEntity.contentEncoding
				ccont =  resEntity.content.text
			}
		}
		finally {
			httpClient.getConnectionManager().shutdown()
		}
		return [cstat: cstat, clen:clen, chunk:chunk, cenc:cenc, ccont:ccont, resHeader:resHeader, statusCode: statusCode]
	}
}

