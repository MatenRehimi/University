public class Marker
{
	private int totalMarks;				//creating fields
	private int numericalMarks;
	private int booleanMarks;
	private int multipleMarks;
	private double firstBoundary;
	private double lowerSecondBoundary;
	private double upperSecondBoundary;

	public int markAttempt (Exam examAttempt, Exam markScheme)		//initialising two objects 
	{
		if (examAttempt.getNumericalAnswer() == markScheme.getNumericalAnswer())		//if statemenents to compare the answers for the mark scheme and attempt. 
		{
			numericalMarks=numericalMarks+markScheme.getNumericalMark();
		}
		else if (examAttempt.getNumericalAnswer()-markScheme.getNumericalAnswer() == 1 || examAttempt.getNumericalAnswer()-markScheme.getNumericalAnswer() == -1)
		{
			numericalMarks=numericalMarks+(markScheme.getNumericalMark()-1);
		}
		else if (1 < examAttempt.getNumericalAnswer()-markScheme.getNumericalAnswer() && examAttempt.getNumericalAnswer()-markScheme.getNumericalAnswer()<6|| -1>examAttempt.getNumericalAnswer()-markScheme.getNumericalAnswer() && examAttempt.getNumericalAnswer()-markScheme.getNumericalAnswer()>-6)
		{
			numericalMarks=numericalMarks+1;
		}
		else
		{
			numericalMarks=numericalMarks;												
		}
		if (examAttempt.getBooleanAnswer() == markScheme.getBooleanAnswer())
		{
			booleanMarks=booleanMarks+1;
		}
		else 
		{
			booleanMarks=booleanMarks;
		}
		if (examAttempt.getMultipleChoiceAnswer1() == markScheme.getMultipleChoiceAnswer1())
		{
			multipleMarks=multipleMarks+1;
		}
		else
		{
			multipleMarks=multipleMarks;
		}
		if (examAttempt.getMultipleChoiceAnswer2() == markScheme.getMultipleChoiceAnswer2())
		{
			multipleMarks=multipleMarks+1;
		}
		else
		{
			multipleMarks=multipleMarks;
		}
		if (examAttempt.getMultipleChoiceAnswer3() == markScheme.getMultipleChoiceAnswer3())
		{
			multipleMarks=multipleMarks+1;
		}
		else
		{
			multipleMarks=multipleMarks;
		}
		totalMarks=numericalMarks+booleanMarks+multipleMarks;
		return totalMarks;
	}
	public int getNumericalMark()		//getter for numerical mark
	{
		return numericalMarks;
	}
	public int getBooleanMark()			//getter for boolean mark
	{									
		return booleanMarks;
	}
	public int getMultipleMark()		//getter for multiple mark
	{
		return multipleMarks;
	}
	public int getTotalMark()			//getter for total mark
	{
		return totalMarks;
	}
	public double convertMarksToClassification(int firstBoundary,int lowerSecondBoundary, int upperSecondBoundary)	
	{
		this.firstBoundary = firstBoundary;					//initialising fields
		this.lowerSecondBoundary = lowerSecondBoundary;
		this.upperSecondBoundary = upperSecondBoundary;
		if (firstBoundary <= getTotalMark())				//if statement to compare the total marks to the boundaries.
		{
			return 1.1;
		}
		else if (getTotalMark() >= upperSecondBoundary && getTotalMark() < firstBoundary)	
		{
			return 2.1;
		}
		else if (getTotalMark() >= lowerSecondBoundary && getTotalMark() < upperSecondBoundary)
		{
			return 2.2;
		}
		else
		{
			return 0;
		}
	}
}














