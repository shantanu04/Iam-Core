package fr.epita.quiz.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;

@Repository
public class MCQChoiceDAO extends GenericDAO<MCQChoice> {

	private static final Logger LOGGER = LogManager.getLogger(MCQChoiceDAO.class);

	@Override
	public List<MCQChoice> search(MCQChoice mcqChoiceCriteria) {
		LOGGER.debug("Start searching MCQ choices for Question: ", mcqChoiceCriteria.getChoiceLabel());
		String hqlString = "from MCQChoice as m where m.question = :question";
		TypedQuery<MCQChoice> searchQuery = em.createQuery(hqlString, MCQChoice.class);
		searchQuery.setParameter("question", mcqChoiceCriteria.getQuestion());

		return searchQuery.getResultList();
	}
	
	// @Override
	// public List<MCQChoice> search(List mcqChoiceCriteria) {
	// LOGGER.debug("Start searching MCQ choices for Question: ",
	// mcqChoiceCriteria.getChoiceLabel());
	// String hqlString = "from MCQChoice as m where m.question in :question";
	// TypedQuery<MCQChoice> searchQuery = em.createQuery(hqlString,
	// MCQChoice.class);
	// searchQuery.setParameter("question", mcqChoiceCriteria.getQuestion());
	//
	// return searchQuery.getResultList();
	// }

	@Override
	public Class<MCQChoice> getType() {
		return MCQChoice.class;
	}

}
