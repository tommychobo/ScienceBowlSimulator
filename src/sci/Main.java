package sci;
import java.util.Scanner;

import Reader.Timer;
public class Main {
	public static void main(String[] args) {
		int points = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("What round would you like to play? (1 to 17)");
		int round = Integer.parseInt(sc.nextLine());
		FilePreparer qSet = new FilePreparer(1, round);
		for(int n = 0; n < qSet.getTossups().length; n++) {
			Timer.readQuestion(qSet.getTossups()[n]);
			String response = sc.nextLine();
			if(response.equals("exit"))
				break;
			boolean correct = qSet.getTossups()[n].submitResponse(response);
			if(correct) {
				Timer.readWords("Correct. 4 points.", 50, 1000);
				points += 4;
				Timer.readQuestion(qSet.getBonuses()[n]);
				response = sc.nextLine();
				if(response.equals("exit"))
					break;
				correct = qSet.getBonuses()[n].submitResponse(response);
				if(correct) {
					Timer.readWords("Correct. 10 points.", 50, 1000);
					points += 10;
				}else {
					Timer.readWords("Incorrect. Correct answer is: "+qSet.getBonuses()[n].formattedAnswer(), 50, 1000);
				}
			}
			else {
				Timer.readWords("Incorrect. Correct answer is: "+qSet.getTossups()[n].formattedAnswer(), 50, 1000);
			}
		}
		System.out.println("you have completed the round. Total points = "+points);
		sc.close();
		qSet.close();
	}
}
