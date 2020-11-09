package sci;
import java.util.ArrayList;

import sci.questions.MCQuestion;
import sci.questions.Question;
public class Chopper {
	
	public static Question[] setUpQuestions(String raw) {
		String[] arr = chopQs(raw);
		Question[] qs = new Question[arr.length];
		for(int n = 0; n < arr.length; n++) {
			String[] qParts = chop(arr[n]);
			if(qParts[2].equals("M")) {
				qs[n] = new MCQuestion(qParts[0], qParts[1], qParts[3], qParts[5], qParts[4], qParts[6].charAt(0));
			}
			else if(qParts[2].equals("S")) {
				qs[n] = new Question(qParts[0], qParts[1], qParts[3], qParts[4]);
			}
			else {
				System.out.println("Something's wrong with this question");
			}
		}
		return qs;
	}
	public static String[] chopQs(String raw) {
		String tu = "TOSS-UP";
		String b = "BONUS";
		ArrayList<String> qs = new ArrayList<String>();
		int lastTUInd = raw.indexOf(tu);
		boolean loop = true;
		while(loop) {
			if(raw.indexOf(tu, lastTUInd) == -1 || raw.indexOf(b, lastTUInd) == -1) {
				loop = false;
				break;
			}
			qs.add(raw.substring(raw.indexOf(tu, lastTUInd), raw.indexOf(b, lastTUInd)));
			lastTUInd++;
			if(raw.indexOf(tu, lastTUInd) == -1 || raw.indexOf(b, lastTUInd) == -1) {
				loop = false;
				break;
			}
			qs.add(raw.substring(raw.indexOf(b, lastTUInd), raw.indexOf(tu, raw.indexOf(b, lastTUInd))));
			lastTUInd = raw.indexOf(tu, lastTUInd);
		}
		qs.add(raw.substring(raw.lastIndexOf(b)));
		String[] questions = new String[qs.size()];
		for(int i = 0; i < qs.size(); i++) {
			questions[i] = qs.get(i);
		}
		return questions;
	}
	/*
	 * 0 = type
	 * 1 = subject
	 * 2 = mult/short
	 * 3 = Question
	 * 4 = Answer
	 * 5 = wxyz
	 * 6 = Letter Ans
	 */
	public static String[] chop(String q) {
		String[] args = new String[1];
		String type = q.substring(0, q.indexOf(' '));
		if(q.indexOf("Multiple Choice") != -1) {
			//is multiple choice
			args = new String[7];
			args[0] = type;
			args[1] = q.substring(q.indexOf(')')+1, q.indexOf("Multiple")).trim();
			args[2] = "M";
			args[3] = q.substring(q.indexOf("Choice")+6, q.indexOf("W)")).trim();
			args[5] = q.substring(q.indexOf("W)"), q.lastIndexOf("ANSWER:")).trim();
			int answerInd = q.lastIndexOf("ANSWER:")+7;
			String wholeAnswer = q.substring(answerInd, q.indexOf("\n", answerInd)).trim();
			args[6] = wholeAnswer.substring(0, 1);
			args[4] = wholeAnswer.substring(2).trim();
		}
		else if(q.indexOf("Short Answer") != -1) {
			//is Short Answer
			args = new String[5];
			args[0] = type;
			args[1] = q.substring(q.indexOf(')')+1, q.indexOf("Short")).trim();
			args[2] = "S";
			args[3] = q.substring(q.indexOf("Answer ")+7, q.lastIndexOf("ANSWER:")).trim();
			int answerInd = q.lastIndexOf("ANSWER:")+7;
			args[4] = q.substring(answerInd, q.indexOf("\n", answerInd)).trim();
		}
		return args;
	}
}
