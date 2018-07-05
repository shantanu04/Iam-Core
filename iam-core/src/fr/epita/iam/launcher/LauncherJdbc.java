package fr.epita.iam.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.dao.IdentityDAO;
import fr.epita.iam.services.dao.IdentityDAOFactory;
import fr.epita.iam.services.dao.JDBCIdentityDAO;
import fr.epita.iam.ui.ConsoleOperations;

/**
 * 
 * @author Shantanu Kamble
 *
 */
public class LauncherJdbc {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// initialize resources
		IdentityDAO dao = null;
		try {
			dao = IdentityDAOFactory.getDAO();
		} catch (final Exception e) {
			// TODO log
			// cannot continue
			return;
		}
		final ConsoleOperations console = new ConsoleOperations();
		// Welcome
		// Authentication

		// Menu

		// Create
		final Identity identity = console.readIdentityFromConsole();
		//dao.create(identity);

		// Search?
		final Identity criteria = console.readCriteriaFromConsole();
		//final List<Identity> resultList = dao.search(criteria);
		//console.displayIdentitiesInConsole(resultList);

		// Update

		// Delete
		console.releaseResources();

	}
	
//	public static void main(String[] args) throws SQLException {
//
//		final ConsoleOperations console = new ConsoleOperations();
//		final JdbcIdentityDAO jdbcDao = new JdbcIdentityDAO();
//		
//		// Create scenario
//		//final Identity identity = console.readIdentityFromConsole();
//		//jdbcDao.create(identity);
//
//		// Search?
//		final Identity criteria = console.readCriteriaFromConsole();
//		final List<Identity> resultList = jdbcDao.search(criteria);
//		if (resultList.size() != 0) {
//			console.displayIdentitiesInConsole(resultList);
//		} else {
//			System.out.println("There is no data result list.");
//		}
//
//		// release resources
//		console.releaseResources();
//
//	}

}
