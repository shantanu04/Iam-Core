package fr.epita.iam.services.identity;

import java.util.function.BiPredicate;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;

/**
 *
 */
public class IdentityDAOFactory {

	private static IdentityDAO currentInstance;

	private static boolean fallbackActivated;

	public static IdentityDAO getDAO() throws Exception {
		final String backendMode = ConfigurationService.getProperty(ConfKey.BACKEND_MODE);

		if (currentInstance == null) {

			currentInstance = getInstance(backendMode);
		}
		if (currentInstance != null && !currentInstance.healthCheck()) {
			fallbackActivated = true;
			final String fallbackMode = ConfigurationService.getProperty(ConfKey.FALLBACK_BACKEND_MODE);
			currentInstance = getInstance(fallbackMode);
		}

		return currentInstance;
	}

	/**
	 * <h3>Description</h3>
	 * <p>
	 * This methods allows to ...
	 * </p>
	 *
	 * <h3>Usage</h3>
	 * <p>
	 * It should be used as follows :
	 *
	 * <pre>
	 * <code> ${enclosing_type} sample;
	 *
	 * //...
	 *
	 * sample.${enclosing_method}();
	 *</code>
	 * </pre>
	 * </p>
	 *
	 * @since $${version}
	 * @see Voir aussi $${link}
	 * @author ${user}
	 *
	 *         ${tags}
	 */
	private static IdentityDAO getInstance(final String backendMode) throws Exception {
		IdentityDAO instance = null;
		switch (backendMode) {
		case "db":
			instance = new JDBCIdentityDAO();
			break;
		case "file":
			try {
				instance = new FileIdentityDAO(new BiPredicate<Identity, Identity>() {

					@Override
					public boolean test(Identity identity1, Identity identity2) {
						return identity1.getEmail().startsWith(identity2.getEmail())
								|| identity1.getDisplayName().startsWith(identity2.getDisplayName());
					}
				});
			} catch (final Exception e) {
				// TODO log
			}
			break;
		case "xml":
			instance = new XMLIdentityDAO();
			break;
		default:
			throw new Exception("not implemented yet");
		}
		return instance;

	}

}
