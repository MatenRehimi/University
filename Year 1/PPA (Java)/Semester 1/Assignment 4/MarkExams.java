public class MarkExams
{
	public static void main (String [] args)					//main method
	{
		NumericalQuestion nqMarkScheme=new NumericalQuestion(198,6);							//stores the actual answer and total mark in three objects(questions) 
		BooleanQuestion bqMarkScheme= new BooleanQuestion(true,1);
		MultipleChoiceQuestion mcqMarkScheme= new MultipleChoiceQuestion(false,false,false,3);
		Exam markScheme=new Exam(nqMarkScheme,bqMarkScheme,mcqMarkScheme,10);					//stores the three objects in another object(exam) which is the mark scheme 
		NumericalQuestion nqAttempt=new NumericalQuestion(196,0);								//stores the attempted answer and mark(before grading) in three objects(questions) 
		BooleanQuestion bqAttempt=new BooleanQuestion(true,0);
		MultipleChoiceQuestion mcqAttempt=new MultipleChoiceQuestion(false,false,false,0);
		Exam examAttempt=new Exam(nqAttempt,bqAttempt,mcqAttempt,0);							//stores the three objects in another object(exam) which is the attempt.
		Marker markExamAttempt=new Marker();													//This initialises the object
		markExamAttempt.markAttempt(examAttempt,markScheme);									//This calls a method which compares(marks) the two objects(exam) 
		System.out.println("Question1: " + markExamAttempt.getNumericalMark() + " out of 6");	
		System.out.println("Question2: " + markExamAttempt.getBooleanMark() + " out of 1");
		System.out.println("Question3: " + markExamAttempt.getMultipleMark() + " out of 3");
		System.out.println("Total Marks: " + markExamAttempt.getTotalMark() + " out of 10");
		System.out.println("Degree:" + markExamAttempt.convertMarksToClassification(9,7,6));	//This converts the marks into a classification
	}
}