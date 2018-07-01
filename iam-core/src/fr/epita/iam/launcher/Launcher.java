package fr.epita.iam.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
import fr.epita.iam.services.identity.IdentityDAO;
import fr.epita.iam.services.identity.IdentityDAOFactory;
import fr.epita.iam.ui.ConsoleOperations;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class Launcher {

	private final static Logger logger = Logger.getLogger(Launcher.class);

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// initialize resources
		IdentityDAO dao = null;
		try {
			dao = IdentityDAOFactory.getDAO();
		} catch (final Exception e) {
			logger.error("Error while getting dao", e);

			return;
		}
		final ConsoleOperations console = new ConsoleOperations();
		// Welcome
		// Authentication

		// Menu
		String choice = "";
		boolean proceed = true;
		while (proceed) {
			choice = console.readChoiceFromConsole();

			switch (choice) {
			case "1":
				// Create
				try {
					final Identity identity = console.readIdentityFromConsole();
					dao.create(identity);
				} catch (final EntityCreationException ece) {
					logger.error(ece.getMessage());
				}
				break;

			case "2":
				// Search
				final Identity criteria = console.readCriteriaFromConsole();
				List<Identity> resultList;
				try {
					resultList = dao.search(criteria);
					console.displayIdentitiesInConsole(resultList);
				} catch (final EntitySearchException e) {
					logger.error(e.getMessage());
				}
				break;

			case "3":
				// Update
				try {
					final Identity updateIdentity = console.readUpdateIdentityFromConsole();
					dao.update(updateIdentity);
				} catch (EntityUpdateException e) {
					logger.error(e.getMessage());
				}
				break;

			case "4":
				// Delete
				break;

			case "5":
				proceed = false;
				logger.error("Exiting...");
				break;
			default:
				logger.error("Invalid choice.");

			}
		}
		console.releaseResources();

	}

}
