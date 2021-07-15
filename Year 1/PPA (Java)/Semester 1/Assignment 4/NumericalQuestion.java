public class NumericalQuestion
	{
		private int answer;			//creating fields
		private int mark;

		public NumericalQuestion(int answer, int mark)		//creating a constructor
		{
			this.answer=answer;					//initialising fields- setting values to the fields
			this.mark=mark;
		} 
		public int getNumericalAnswer()			//getter for numerical answer
		{
			return this.answer;
		}
		public int getNumericalMark()			// getter for numerical mark
		{
			return this.mark;
		}
	}