//package fr.epita.maths.test;
//
//import javax.inject.Inject;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import fr.epita.quiz.datamodel.MCQChoice;
//import fr.epita.quiz.datamodel.Question;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/applicationContext.xml" })
//public class TestJPA {
//
//	@Inject
//	private SessionFactory sf;
//
//	@Test
//	public void testJPA() {
//
//		// given
//		Question question = new Question();
//		question.setQuestionLabel("What is JPA?");
//
//		// when
//		Session session = sf.openSession();
//		Transaction tx = session.beginTransaction();
//		session.save(question);
//		tx.commit();
//		session.close();
//
//		// then
//		Session session2 = sf.openSession();
//		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
//
//		Assert.assertNotEquals(0, searchQuery.list().size());
//		session2.close();
//
//	}
//
//	@Test
//	public void testJPAMCQ() {
//
//		// Given
//		Question question = new Question();
//		question.setQuestionLabel("What is JPA?");
//		MCQChoice choice1 = new MCQChoice();
//		choice1.setChoiceLabel("it is a dependency injection framework");
//		choice1.setValid(false);
//
//		MCQChoice choice2 = new MCQChoice();
//		choice2.setChoiceLabel("it is a specification to normalize persistence in java");
//		choice2.setValid(true);
//
//		choice1.setQuestion(question);
//		choice2.setQuestion(question);
//
//		// when
//		Session session = sf.openSession();
//		Transaction tx = session.beginTransaction();
//		session.save(question);
//		session.save(choice1);
//		session.save(choice2);
//		tx.commit();
//
//		session.close();
//
//		// then
//		Session session2 = sf.openSession();
//		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
//
//		Assert.assertNotEquals(0, searchQuery.list().size());
//
//		Query<MCQChoice> searchQueryMCQ = session2.createQuery("from MCQChoice", MCQChoice.class);
//		Assert.assertEquals(2, searchQueryMCQ.list().size());
//
//		session2.close();
//
//	}
//
//}
