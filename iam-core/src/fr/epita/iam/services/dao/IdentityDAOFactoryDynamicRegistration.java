//package fr.epita.iam.services.dao;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import fr.epita.iam.services.conf.ConfKey;
//import fr.epita.iam.services.conf.ConfigurationService;
//
///**
// * 
// * @author Shantanu Kamble
// *
// */
//
///**
// * 
// * This class is a factory class used to dynamically register the DAOs
// *
// */
//public class IdentityDAOFactoryDynamicRegistration {
//
//	static Map<String, IdentityDAO> registeredDAOs = new LinkedHashMap<>();
//	
//
//	/**
//	 * This method is used to get the DAO object dynamically depending upon the
//	 * backend mode
//	 * 
//	 * @return the instance of IdentityDAO
//	 * @throws Exception
//	 */
//	public static IdentityDAO getDAO() throws Exception {
//		return registeredDAOs.get(ConfigurationService.getProperty(ConfKey.BACKEND_MODE));
//	}
//
//}
