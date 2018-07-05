package fr.epita.iam.tests.db;

import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.dao.IdentityDAO;
import fr.epita.iam.services.dao.IdentityDAOFactory;

/**
 *
 */
public class TestJDBCDAO {

	public static void main(String[] args) throws Exception {
		// testWriteThenDisplayList();

		// given
		final IdentityDAO dao = IdentityDAOFactory.getDAO();
		final Identity quentin = new Identity("kaushik", "7893", "kk@gmail.com");
		dao.create(quentin);

		final Identity criteria = new Identity("kaushik", null, "kk");

		// when
		final List<Identity> resultList = dao.search(criteria);

		// then
		boolean found = false;
		for (final Identity identity : resultList) {
			if (identity.getUid().equals(quentin.getUid())) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("The search method did not work");
		}
		System.out.println("Search was successful!");

	}

}
