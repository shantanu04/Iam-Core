package fr.epita.iam.ui;

import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;

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
		System.out.println("\n\n<-----MENU----->");
		System.out.println("1) Create \n2)Search \n3)Update \n4)Delete \n5) Exit");
		System.out.println("Enter your choice: ");
		return scanner.nextLine();
	}

	/**
	 * This method is used to read user credentials from console
	 * 
	 * @return the user
	 */
	public User readUserCredentialsFromConsole() {
		final User user = new User();
		System.out.println("Please enter the username: ");
		String line = scanner.nextLine();
		user.setUsername(line);
		System.out.println("Please enter the password: ");
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
		final Identity identity = new Identity();
		System.out.println("Please input the display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		System.out.println("Please input the email");
		line = scanner.nextLine();
		identity.setEmail(line);
		System.out.println("Please input uid");
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
		System.out.println("Enter criteria");
		final Identity identity = new Identity();
		System.out.println("Please input the criterion for display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		System.out.println("Please input the criterion for email");
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
		System.out.println("Enter identity to update");
		final Identity identity = new Identity();
		System.out.println("Please input the Identity UID to update : ");
		String line = scanner.nextLine();
		identity.setUid(line);
		System.out.println("Please input the display name to update : ");
		line = scanner.nextLine();
		identity.setDisplayName(line);
		System.out.println("Please input the new email id : ");
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
		System.out.println("Enter identity to delete");
		final Identity identity = new Identity();
		System.out.println("Please input the Identity UID to delete : ");
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
			System.out.println("No elements in the identity list.");
		}

		int i = 1;
		for (final Identity identity : identities) {
			System.out.print(i++);
			System.out.println(" - " + identity);
		}
	}

	/**
	 * This method is used to release resources
	 */
	public void releaseResources() {
		scanner.close();
	}
}
