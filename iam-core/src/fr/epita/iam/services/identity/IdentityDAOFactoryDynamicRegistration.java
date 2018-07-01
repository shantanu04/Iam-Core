package fr.epita.iam.services.identity;

import java.util.LinkedHashMap;
import java.util.Map;

import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;

/**
 *
 */
public class IdentityDAOFactoryDynamicRegistration {

	static Map<String, IdentityDAO> registeredDAOs = new LinkedHashMap<>();

	public static IdentityDAO getDAO() throws Exception {
		return registeredDAOs.get(ConfigurationService.getProperty(ConfKey.BACKEND_MODE));
	}

}
