package preliminaries;

public class Marker {

	public int markAttempt(Exam examAttempt, Exam markScheme) {
		
		if ( examAttempt.lookAtQuestion1Answer()== markScheme.lookAtQuestion1Answer()) {
			
			examAttempt.giveMarkQuestion1(markScheme.readMarkQuestion1());
			
		} else if ( examAttempt.lookAtQuestion1Answer() == markScheme.lookAtQuestion1Answer() - 1 
				 || examAttempt.lookAtQuestion1Answer() == markScheme.lookAtQuestion1Answer() + 1  ) {
			
			examAttempt.giveMarkQuestion1(markScheme.readMarkQuestion1()- 1);
			
		} else if ( examAttempt.lookAtQuestion1Answer() >= markScheme.lookAtQuestion1Answer() - 5 
				 && examAttempt.lookAtQuestion1Answer() <= markScheme.lookAtQuestion1Answer() + 5  ) {
		
			examAttempt.giveMarkQuestion1(1);;
		
		} else {
			
			examAttempt.giveMarkQuestion1(0);;
			
		}
		
		if ( examAttempt.lookAtQuestion2Answer()== markScheme.lookAtQuestion2Answer()) {
			
			examAttempt.giveMarkQuestion2(markScheme.readMarkQuestion2());
			
		} else {
			
			examAttempt.giveMarkQuestion2(0);;
			
		}
		
		if ( examAttempt.lookAtQuestion3Option1Answer() == markScheme.lookAtQuestion3Option1Answer() ) {
			
			examAttempt.giveMarkQuestion3(examAttempt.readMarkQuestion3() + 1);
	
		}
		
	    if ( examAttempt.lookAtQuestion3Option2Answer() == markScheme.lookAtQuestion3Option2Answer()) {
			
			examAttempt.giveMarkQuestion3(examAttempt.readMarkQuestion3() + 1);
			
		}
		
	    if ( examAttempt.lookAtQuestion3Option3Answer() == markScheme.lookAtQuestion3Option3Answer() ) {
			
			examAttempt.giveMarkQuestion3(examAttempt.readMarkQuestion3() + 1);
			
		}
	    
	    int totalMark = examAttempt.readMarkQuestion1() + examAttempt.readMarkQuestion2() + examAttempt.readMarkQuestion3();
	    		
	    examAttempt.writeTotalMark(totalMark);
	    
	    return totalMark;
	    
	}
	
	public double convertMarksToClassification(int mark, int firstBoundary, int upperSecondBoundary, int lowerSecondBoundary) {
		
		if ( mark >= firstBoundary ) {
			
			return 1.1;
			
		} else if ( mark >= upperSecondBoundary ) {
			
			return 2.1;
			
		} else if ( mark >= lowerSecondBoundary ) {
			
			return 2.2;
			
		} else {
			
			return 0.0;
			
		}
	
	}
	
}
