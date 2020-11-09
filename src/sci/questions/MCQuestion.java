package sci.questions;

public class MCQuestion extends Question{
	String wxyz;
	char letterAns;
	public MCQuestion(String type, String subject, String question, String wxyz, String answer, char letterAns) {
		super(type, subject, question, answer);
		this.wxyz = wxyz;
		this.letterAns = letterAns;
	}
	
	@Override
	public boolean submitResponse(String r) {
		return r.equalsIgnoreCase(""+letterAns) || r.equalsIgnoreCase(answer);
	}
	@Override
	public String formattedAnswer() {
		return letterAns+") "+answer;
	}
	public String getWxyz() {
		return wxyz;
	}
	public char getLetterAns() {
		return letterAns;
	}
	
}
