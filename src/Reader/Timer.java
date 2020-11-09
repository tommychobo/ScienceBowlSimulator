package Reader;

import sci.questions.MCQuestion;
import sci.questions.Question;

public class Timer {
	//maybe will make this multithreaded at some point, we'll see how it goes
	public static void readWords(String words, int ms, int msPause) {
		long now = System.currentTimeMillis();
		int ind = 0;
		while(ind < words.length()) {
			if(System.currentTimeMillis() > now + ms) {
				System.out.print(words.charAt(ind));
				ind++;
				now = System.currentTimeMillis();
			}
		}
		while(System.currentTimeMillis() < now + msPause) {}
		System.out.println();
	}
	public static void readQuestion(Question q) {
		readWords(q.getType(), 50, 500);
		readWords(q.getSubject(), 40, 500);
		if(q instanceof MCQuestion) {
			MCQuestion mq = (MCQuestion)q;
			readWords("Multiple Choice", 40, 1000);
			readWords(mq.getQuestion(), 10, 500);
			readWords(mq.getWxyz(), 15, 100);
		}
		else {
			readWords("Short Answer", 40, 1000);
			readWords(q.getQuestion(), 10, 100);
		}
	}
}
