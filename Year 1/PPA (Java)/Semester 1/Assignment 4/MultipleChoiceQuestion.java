public class MultipleChoiceQuestion
{
	private boolean option1;			//creating fields
	private boolean option2;
	private boolean option3;
	private int mark;

	public MultipleChoiceQuestion(boolean option1, boolean option2,boolean option3, int mark)	//creating constructor
	{
		this.option1=option1;			//initialising fields
		this.option2=option2;			
		this.option3=option3;
		this.mark=mark;
	}
	public boolean getMultipleChoiceAnswer1()		//getter for option1
	{
		return this.option1;
	}
	public boolean getMultipleChoiceAnswer2()		//getter for option2
	{
		return this.option2;
	}
	public boolean getMultipleChoiceAnswer3()		//getter for option3
	{
		return this.option3;
	}
	public int getMultipleMark()					//getter for mark in multiple choice question
	{
		return this.mark;
	}
}