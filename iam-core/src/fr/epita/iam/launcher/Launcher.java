package fr.epita.iam.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
import fr.epita.iam.logger.Logger;
import fr.epita.iam.services.dao.IdentityDAO;
import fr.epita.iam.services.dao.IdentityDAOFactory;
import fr.epita.iam.services.dao.JDBCUserDAO;
import fr.epita.iam.ui.ConsoleOperations;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class Launcher {

	private final static Logger logger = new Logger(Launcher.class);

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
		System.out.println("\n Welcome to Identity Access Management System..!! \n\n");

		// Authentication
		User userLogin = console.readUserCredentialsFromConsole();
		JDBCUserDAO userDao = new JDBCUserDAO();
		boolean isValid = userDao.checkLogin(userLogin);

		if (!isValid) {
			System.out.println("You have entered wrong credentials. Please try again.");
			return;
		} else {
			System.out.println("Authentication successful");
		}

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
					logger.info("Created identity successfully");
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
					logger.info("Search successful");
				} catch (final EntitySearchException e) {
					logger.error(e.getMessage());
				}
				break;

			case "3":
				// Update
				try {
					final Identity updateIdentity = console.readUpdateIdentityFromConsole();
					dao.update(updateIdentity);
					logger.info("Updated identity successfully");
				} catch (EntityUpdateException e) {
					logger.error(e.getMessage());
				}
				break;

			case "4":
				// Delete
				try {
					final Identity deleteIdentity = console.readDeleteIdentityFromConsole();
					dao.delete(deleteIdentity);
					logger.info("Deleted identity successfully");
				} catch (EntityDeletionException e) {
					logger.error(e.getMessage());
				}
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