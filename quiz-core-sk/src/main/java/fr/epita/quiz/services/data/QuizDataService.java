package fr.epita.quiz.services.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

@Repository
public class QuizDataService {

	private static final Logger LOGGER = LogManager.getLogger(QuizDataService.class);

	@Inject
	QuestionDAO questionDAO;

	@Inject
	MCQChoiceDAO mcqDAO;

	public void createQuestionWithChoices(Question question, List<MCQChoice> mcqChoiceList) {
		LOGGER.info("Create MCQs");
		questionDAO.create(question);
		for (MCQChoice choice : mcqChoiceList) {
			choice.setQuestion(question);
			mcqDAO.create(choice);
		}
	}

	public Map<Question, List<MCQChoice>> findQuestionsAndChoices(Question question) {
		LOGGER.info("Find Question and Choices for given question");
		Map<Question, List<MCQChoice>> questionsAndChoices = new LinkedHashMap<Question, List<MCQChoice>>();

		List<Question> questionsList = questionDAO.search(question);

		// fetch mcqChoices for a Question and put it in the map
		for (Question currentQuestion : questionsList) {
			MCQChoice mcqChoiceCriteria = new MCQChoice();
			mcqChoiceCriteria.setQuestion(currentQuestion);
			List<MCQChoice> mcqChoiceList = mcqDAO.search(mcqChoiceCriteria);
			questionsAndChoices.put(currentQuestion, mcqChoiceList);
		}

		return questionsAndChoices;

	}

	public Map<Question, List<MCQChoice>> findAllQuestionsWithMCQChoices() {
		LOGGER.info("Find all questions with choices");
		Map<Question, List<MCQChoice>> questionsAndChoices = new LinkedHashMap<Question, List<MCQChoice>>();

		List<Question> questionsList = questionDAO.searchAll();

		for (Question currentQuestion : questionsList) {
			MCQChoice mcqChoiceCriteria = new MCQChoice();
			mcqChoiceCriteria.setQuestion(currentQuestion);
			List<MCQChoice> mcqChoiceList = mcqDAO.search(mcqChoiceCriteria);
			questionsAndChoices.put(currentQuestion, mcqChoiceList);
		}

		return questionsAndChoices;
	}

	/**
	 * This method is used to get Question by providing Id of the question.
	 * 
	 * @param id
	 * @return the question
	 */
	public Question getQuestionById(Long id) {
		Question question = questionDAO.getById(id);
		return question;
	}

	public void updateQuestionAndChoices(Question question, List<MCQChoice> mcqChoiceList) {
		LOGGER.debug("Update question and choices");
		questionDAO.update(question);
		for (MCQChoice currentChoice : mcqChoiceList) {
			if (currentChoice.getChoiceLabel() != null && currentChoice.getValid() != null) {
				mcqDAO.update(currentChoice);
			}
		}
	}

	/**
	 * This method is used to delete a MCQ along with choices.
	 * 
	 * @param question
	 * @return true if deletion is successful else return false
	 */
	public Boolean deleteQuestionWithChoices(Question question) {
		LOGGER.debug("Delete question with choices");
		MCQChoice choiceCriteria = new MCQChoice();
		choiceCriteria.setQuestion(question);
		List<MCQChoice> mcqChoiceList = mcqDAO.search(choiceCriteria);

		if (question != null && question.getId() != null) {
			// Delete mcq choices one by one
			for (MCQChoice currentChoice : mcqChoiceList) {
				LOGGER.debug("Deleting MCQ choice: " + currentChoice.getId());
				mcqDAO.delete(currentChoice);
			}
			questionDAO.delete(question);
			return true;
		}
		return false;
	}

}
