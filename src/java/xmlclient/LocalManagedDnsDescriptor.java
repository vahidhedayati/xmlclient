package xmlclient;

/**
 * <p>
 * Constructs the LocalManagedDns and returns references to it.
 * </p>
 * 
 * <p>
 * This class assigns the name of this name service provider, which is
 * "dns,LocalManagedDns".
 * </p>
 * 
 * @version $Id$
 * @author Roman Kuzmik (rkuzmik@gmail.com)
 */
@SuppressWarnings("restriction")
public class LocalManagedDnsDescriptor implements
		sun.net.spi.nameservice.NameServiceDescriptor {

	public static final String DNS_PROVIDER_NAME = "LocalManagedDns";

	private static sun.net.spi.nameservice.NameService nameService = null;

	static {
		nameService = new LocalManagedDns();
	}

	/**
	 * @return The string "dns"
	 */
	public String getType() {
		return "dns";
	}

	/**
	 * @return The string "dnsjava"
	 */
	public String getProviderName() {
		return DNS_PROVIDER_NAME;
	}

	/**
	 * This doesn't actually create a name service provider, it returns a
	 * reference to the one that was already created as class load time.
	 * 
	 * @return The dnsjava name service provider
	 */

	public sun.net.spi.nameservice.NameService createNameService() {
		return nameService;
	}
}