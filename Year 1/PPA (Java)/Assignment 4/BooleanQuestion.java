public class BooleanQuestion
	{
		private boolean answer;					//creating fields
		private int mark;

		public BooleanQuestion(boolean answer, int mark)		//creating constructor
		{
			this.answer=answer;									//initialising fields
			this.mark=mark;
		}
		public boolean getBooleanAnswer()						//getter for boolean answer
		{
			return this.answer;
		}
		public int getBooleanMark()								//getter for boolean mark
		{
			return this.mark;
		}
	}