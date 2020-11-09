package sci.questions;

public class Question {
	String type;
	String subject;
	String question;
	String answer;
	public Question(String type, String subject, String question, String answer) {
		this.type = type;
		this.subject = subject;
		this.question = question;
		this.answer = answer;
	}
	public boolean submitResponse(String r) {
		if(answer.indexOf("(ACCEPT:") == -1) {
			return r.equalsIgnoreCase(answer);
		}
		else {
			return r.equalsIgnoreCase(answer.substring(0, answer.indexOf("(ACCEPT:")).trim()) 
					|| r.equalsIgnoreCase(answer.substring(answer.indexOf("(ACCEPT:")+8, answer.lastIndexOf(")")).trim());
		}
	}
	public String formattedAnswer() {
		if(answer.indexOf("(ACCEPT:") == -1)
			return answer;
		return answer.substring(0, answer.indexOf("(ACCEPT:")).trim();
	}
	public String getType() {
		return type;
	}
	public String getSubject() {
		return subject;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
}
