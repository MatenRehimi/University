package preliminaries;

public class Exam {
	
	private NumericalQuestion question1;
	private BooleanQuestion question2;
	private MultipleChoiceQuestion question3;
	private int totalMark;
	
	public Exam(NumericalQuestion question1, BooleanQuestion question2, MultipleChoiceQuestion question3, int totalMark) {
		
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.totalMark = totalMark;
		
	}
	
	public int lookAtQuestion1Answer() {
		
		return question1.lookAtAnswer();
		
	}
	
	public boolean lookAtQuestion2Answer() {
		
		return question2.lookAtAnswer();
	
	}
	
	public boolean lookAtQuestion3Option1Answer() {
		
		return question3.lookAtOption1Answer();
		
	}

	public boolean lookAtQuestion3Option2Answer() {
		
		return question3.lookAtOption2Answer();
		
	}

	public boolean lookAtQuestion3Option3Answer() {
		
		return question3.lookAtOption3Answer();
		
	}
	
	public void giveMarkQuestion1(int mark) {
		
		question1.giveMark(mark);
		
	}
	
	public void giveMarkQuestion2(int mark) {
		
		question2.giveMark(mark);
		
	}
	
	public void giveMarkQuestion3(int mark) {
		
		question3.giveMark(mark);
		
	}
	
	public int readMarkQuestion1() {
		
		return question1.readMark();
		
	}
	
	public int readMarkQuestion2() {
		
		return question2.readMark();
		
	}
	
	public int readMarkQuestion3() {
		
		return question3.readMark();
		
	}
	
	public void writeTotalMark(int totalMark) {
		
		this.totalMark = totalMark;
				
	}
	
	public int readTotalMark() {
		
		return totalMark;
		
	}
	

}

