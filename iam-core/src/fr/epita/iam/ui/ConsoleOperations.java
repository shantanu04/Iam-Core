package fr.epita.iam.ui;

import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.User;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class ConsoleOperations {

	private final Scanner scanner;

	public ConsoleOperations() {
		scanner = new Scanner(System.in);
	}

	public String readChoiceFromConsole() {
		System.out.println("\n\n<-----MENU----->");
		System.out.println("1) Create \n2)Search \n3)Update \n4)Delete \n5) Exit");
		System.out.println("Enter your choice: ");
		String choice = scanner.nextLine();
		return choice;
	}

	/**
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

	public Identity readDeleteIdentityFromConsole() {
		System.out.println("Enter identity to delete");
		final Identity identity = new Identity();
		System.out.println("Please input the Identity UID to delete : ");
		String line = scanner.nextLine();
		identity.setUid(line);

		return identity;
	}

	public void displayIdentitiesInConsole(List<Identity> identities) {
		// check if list is empty
		if (identities.size() == 0) {
			System.out.println("No elements in the identity list.");
		}

		int i = 1;
		for (final Identity identity : identities) {
			System.out.print(i++);
			System.out.println(" - " + identity);
		}
	}

	public void releaseResources() {
		scanner.close();
	}
}
