package sci;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import sci.questions.Question;

public class FilePreparer {
	private File file;
	private PDDocument pdf;
	private PDFTextStripper stripper;
	private String rawText;
	private Question[] allQuestions;
	private Question[] tossups;
	private Question[] bonuses;
	public FilePreparer(int set, int num) {
	  try {
		file = new File("./res/set"+set+"/r"+num+".pdf");
		pdf = PDDocument.load(file);
		stripper = new PDFTextStripper();
		rawText = stripper.getText(pdf);
		allQuestions = Chopper.setUpQuestions(rawText);
		tossups = new Question[allQuestions.length/2];
		bonuses = new Question[allQuestions.length/2];
		for(int n = 0; n < allQuestions.length; n+=2) {
			tossups[n/2] = allQuestions[n];
			bonuses[n/2] = allQuestions[n+1];
		}
	  }catch(IOException e) {
		e.printStackTrace();
	  }
		
	}
	public void close() {
		try {
			pdf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public String getRawText() {
		return rawText;
	}
	public Question[] getQuestions() {
		return allQuestions;
	}
	public Question[] getTossups() {
		return tossups;
	}
	public Question[] getBonuses() {
		return bonuses;
	}
}
