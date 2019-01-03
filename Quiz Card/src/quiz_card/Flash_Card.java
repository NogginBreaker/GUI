package quiz_card;
/*
 * Zachary Moncur
 * 1/3/19
 */
public class Flash_Card {
	// Attributes of a quiz card
	private String question;
	private String answer;
	
	
	// Constructor will have a string for the question/answer
	public Flash_Card(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	
	// Setting up the getters and setters
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	

	
}
