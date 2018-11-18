package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MCQChoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Boolean valid;

	private String choiceLabel;

	@ManyToOne
	private Question question;

	public MCQChoice() {
		// Default constructor
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the choiceLabel
	 */
	public String getChoiceLabel() {
		return choiceLabel;
	}

	/**
	 * @param choiceLabel
	 *            the choiceLabel to set
	 */
	public void setChoiceLabel(String choiceLabel) {
		this.choiceLabel = choiceLabel;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
