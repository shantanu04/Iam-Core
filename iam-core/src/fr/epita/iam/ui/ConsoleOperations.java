package fr.epita.iam.ui;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;
import fr.epita.iam.launcher.Launcher;

/**
 * <h3>Description</h3>
 * <p>
 * This class contains all the console related operations.
 * </p>
 * 
 * @author Shantanu Kamble
 *
 */
public class ConsoleOperations {

	/** The logger */
	private static final Logger logger = LogManager.getLogger(ConsoleOperations.class);

	/** The scanner */
	private final Scanner scanner;

	/**
	 * The constructor
	 */
	public ConsoleOperations() {
		scanner = new Scanner(System.in);
	}

	/**
	 * This method is used to read user choice from console
	 * 
	 * @return
	 */
	public String readChoiceFromConsole() {
		logger.info("\n\n<-----MENU----->\n");
		logger.info("1) Create \n2) Search \n3) Update \n4) Delete \n5) Exit\n");
		logger.info("Enter your choice : ");
		return scanner.nextLine();
	}

	/**
	 * This method is used to read user credentials from console
	 * 
	 * @return the user
	 */
	public User readUserCredentialsFromConsole() {
		final User user = new User();
		logger.info("Please enter the username : ");
		String line = scanner.nextLine();
		user.setUsername(line);
		logger.info("\nPlease enter the password : ");
		line = scanner.nextLine();
		user.setPasskey(line);

		return user;
	}

	/**
	 * This method is used to read identity details from console
	 * 
	 * @return the identity
	 */
	public Identity readIdentityFromConsole() {
		logger.info("\nEnter details of identity to be created");
		final Identity identity = new Identity();
		logger.info("\nPlease input the display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		logger.info("\nPlease input the email-id : ");
		line = scanner.nextLine();
		identity.setEmail(line);
		logger.info("\nPlease input uid : ");
		line = scanner.nextLine();
		identity.setUid(line);
		return identity;
	}

	/**
	 * This method is used to read search criteria from console
	 * 
	 * @return the identity
	 */
	public Identity readCriteriaFromConsole() {
		logger.info("\nEnter criteria for search");
		final Identity identity = new Identity();
		logger.info("\nPlease input the criterion for display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		logger.info("\nPlease input the criterion for email-id : ");
		line = scanner.nextLine();
		identity.setEmail(line);

		return identity;
	}

	/**
	 * This method is used to read updated identity details from console
	 * 
	 * @return the identity
	 */
	public Identity readUpdateIdentityFromConsole() {
		logger.info("\nEnter identity to update");
		final Identity identity = new Identity();
		logger.info("\nPlease input the Identity UID to update : ");
		String line = scanner.nextLine();
		identity.setUid(line);
		logger.info("\nPlease input the display name to update : ");
		line = scanner.nextLine();
		identity.setDisplayName(line);
		logger.info("\nPlease input the new email-id : ");
		line = scanner.nextLine();
		identity.setEmail(line);

		return identity;
	}

	/**
	 * This method is used to read delete identity details from console
	 * 
	 * @return the identity
	 */
	public Identity readDeleteIdentityFromConsole() {
		logger.info("\nEnter identity to delete");
		final Identity identity = new Identity();
		logger.info("\nPlease input the Identity UID to delete : ");
		String line = scanner.nextLine();
		identity.setUid(line);

		return identity;
	}

	/**
	 * This method is used to display identity details in console
	 * 
	 * @param identities
	 */
	public void displayIdentitiesInConsole(List<Identity> identities) {
		// check if list is empty
		if (identities.isEmpty()) {
			logger.error("There are no elements to show in the identity list.\n");
		}

		int i = 1;
		for (final Identity identity : identities) {
			logger.info(i++ + " - " + identity);
		}
	}

	/**
	 * This method is used to release resources
	 */
	public void releaseResources() {
		scanner.close();
	}
}
