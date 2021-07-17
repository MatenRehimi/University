public class Exam
{
	private NumericalQuestion question1;			//creating fields
	private BooleanQuestion question2;
	private MultipleChoiceQuestion question3;
	private int totalMark;

	public Exam(NumericalQuestion question1,BooleanQuestion question2, MultipleChoiceQuestion question3, int totalMark) //creating constructor and initialising objects
	{
		this.question1=question1;		//initialising fields
		this.question2=question2;
		this.question3=question3;
		this.totalMark=totalMark;
	}
	public int getNumericalAnswer()				//getter for numerical answer
	{
		return question1.getNumericalAnswer();
	}
	public int getNumericalMark()				//getter for numerical mark
	{
		return question1.getNumericalMark();	
	}
	public boolean getBooleanAnswer()			//getter for boolean answer
	{
		return question2.getBooleanAnswer();
	}
	public int getBooleanMark()					//getter for boolean mark
	{
		return question2.getBooleanMark();
	}
	public boolean getMultipleChoiceAnswer1()			//getter for option 1
	{
		return question3.getMultipleChoiceAnswer1();
	}
	public boolean getMultipleChoiceAnswer2()			//getter for option 2
	{
		return question3.getMultipleChoiceAnswer2();
	}
	public boolean getMultipleChoiceAnswer3()			//getter for option 3
	{
		return question3.getMultipleChoiceAnswer3();
	}
}