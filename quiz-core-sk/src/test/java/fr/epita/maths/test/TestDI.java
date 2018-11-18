//package fr.epita.maths.test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.sql.DataSource;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/applicationContext.xml" })
//public class TestDI {
//
//	private static final Logger LOGGER = LogManager.getLogger(TestDI.class);
//
//	@Inject
//	@Named("firstQuery")
//	String query;
//
//	@Inject
//	@Named("test1")
//	String testValue;
//
//	@Inject
//	DataSource ds;
//
//	@Test
//	public void firstDITest() {
//		LOGGER.info("DI value: " + query);
//		Assert.assertNotNull(query);
//	}
//
//	@Test
//	public void dsInjectionTest() throws SQLException {
//		Connection connection = ds.getConnection();
//		String schema = connection.getSchema();
//		Assert.assertEquals("PUBLIC", schema);
//		connection.close();
//	}
//
//	@Test
//	public void secondDITest() {
//		LOGGER.info("Test value is: " + testValue);
//		Assert.assertNotNull(testValue);
//	}
//
//}
