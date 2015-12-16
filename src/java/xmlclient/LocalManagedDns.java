package xmlclient;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;

import sun.net.spi.nameservice.NameService;

/**
 * 
 * @version $Id$
 * @author Roman Kuzmik (rkuzmik@gmail.com)
 */
@SuppressWarnings("restriction")
public class LocalManagedDns implements NameService {

	NameService defaultDnsImpl = new DNSJavaNameService();

	/**
	 * @see sun.net.spi.nameservice.NameService#getHostByAddr(byte[])
	 */
	public String getHostByAddr(byte[] ip) throws UnknownHostException {
		return defaultDnsImpl.getHostByAddr(ip);
	}

	/**
	 * @see sun.net.spi.nameservice.NameService#lookupAllHostAddr(java.lang.String)
	 */
	public InetAddress[] lookupAllHostAddr(String name)
			throws UnknownHostException {

		String ipAddress = NameStore.getInstance().get(name);
		if (StringUtils.isNotEmpty(ipAddress)) {
			System.out.println ("--- WE HAVE ONE");
			InetAddress address = Inet4Address.getByAddress(Util
					.textToNumericFormat(ipAddress));
			return new InetAddress[] { address };
		} else {
			return defaultDnsImpl.lookupAllHostAddr(name);
		}
	}
	
	public InetAddress[] lookupAllHostAddr2(String name, String ipAddress)
			throws UnknownHostException {
		if (StringUtils.isNotEmpty(ipAddress)) {
			InetAddress address = Inet4Address.getByAddress(Util
					.textToNumericFormat(ipAddress));
			return new InetAddress[] { address };
		} else {
			return defaultDnsImpl.lookupAllHostAddr(name);
		}
	}

}
