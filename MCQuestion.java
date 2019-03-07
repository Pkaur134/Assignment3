package Final;

/**
 * Put your JavaDocs here.
 * 
 * @author Wendi Jollymore, updated by M. Mohiuddin on 3/1/18
 * 
 * @author Your Name Here
 */
public class MCQuestion extends Question {

	private char answer = '.'; // the correct answer to this question
	private String[] options = null; // the list of multiple choice options

	/**
	 * Constructs a default MC Question object.
	 */
	public MCQuestion() {
		super();
		/*
		 * TODO MCQ.1.1: assign multiple choice question enum to the
		 * questionType field here
		 */
		questionType = QuestionType.MULTIPLE_CHOICE;
	}

	/**
	 * Constructs a MC Question object with a specific id, text, answer, and
	 * list of options.
	 * 
	 * @param id
	 *            the question id
	 * @param text
	 *            the question text
	 * @param answer
	 *            the correct answer
	 * @param ops
	 *            the list of choice options
	 */
	public MCQuestion(int id, String text, char answer, String... ops) {
		super(id, text);
		setOptions(ops);
		setAnswer(answer);
		/*
		 * TODO MCQ.1.2: assign multiple choice question enum to the
		 * questionType field here
		 */
		questionType = QuestionType.MULTIPLE_CHOICE;
	}

	/**
	 * Sets the answer member to a specific value. The answer is the letter of
	 * the multiple-choice option that has the correct answer to the question.
	 * The answer must be a letter and it must be in a valid range (e.g. if
	 * there are 4 options, then the answer letter must be a, b, c, or d).
	 * 
	 * @param answer
	 *            this is questions correct answer
	 * @throws IllegalArgumentException
	 *             if the answer is not valid
	 */
	public void setAnswer(char answer) throws IllegalArgumentException {
		// conver the answer to lower-case
		char a = Character.toLowerCase(answer);
		// subtract 97 to turn it into an index for the options array
		int index = a - 97;
		// if the answer is a letter and it's within the valid range of
		// array indexes, set it
		if (Character.isLetter(a) && index >= 0 && index < options.length) {
			this.answer = a;
		} else { // invalid answer - throw exception
			throw new IllegalArgumentException(answer + " is not a valid answer option.");
		}
	}

	/**
	 * Retrieves the correct answer letter for this question.
	 * 
	 * @return the questions correct answer
	 */
	public char getAnswer() {
		return answer;
	}

	/*
	 * TODO MCQ.2.0: setOptions() mutator method: Make sure that the variable
	 * argument list is not empty (length is not 0 or less. If it is, throw an
	 * IllegalArgumentException. You also need to make sure that each element of
	 * the list has something in it (no null-strings), othewise, throw an
	 * exception. If everything is ok, arraycopy() the list parameter contents
	 * into the array data member options. Don't forget the Javadocs and DON'T
	 * use the specs above in the Javadocs: write friendly and professional docs
	 * in your own words.
	 */
	public void setOptions(String... str) {
		if (str.length > 0) {
			for (int iOption = 0; iOption < str.length; iOption++) {
				if (str[iOption] == null) 
					throw new IllegalArgumentException("The option " + (iOption+1) + " is null");
			}
			System.arraycopy(str, 0, options, 0, str.length);
		} else {
			throw new IllegalArgumentException("Atleast 2 options must be passed");
		}

	}

	/*
	 * TODO MCQ.3.0: getOptions() accessor method: A bit more code than your
	 * typical accessor because you need to return an arraycopy() of the options
	 * array. Don't forget the Javadocs and DON'T use the specs above in the
	 * Javadocs: write friendly and professional docs in your own words.
	 */
	public String[] getOptions() {
		String[] copyOptions = new String[options.length];
		System.arraycopy(options, 0, copyOptions, 0, copyOptions.length);
		return copyOptions;

	}

	/*
	 * TODO MCQ.4.0: isCorrect() override: Check that the param is a String or
	 * Character (the programmer could capture the user's answer using either
	 * one). If it's not, then consider the answer incorrect. Otherwise, convert
	 * the guess param into a char and compare it to answer so you can return
	 * whether or not the guess correct. Don't forget the Javadocs and DON'T use
	 * the specs above in the Javadocs: write friendly and professional docs in
	 * your own words.
	 */
	public boolean isCorrect(Object guess) {
		if (guess.getClass().equals("String")) {
			return (char) ((String) guess).charAt(0) == this.answer;
		} 
		if (guess.getClass().equals("char") || guess.getClass().equals("Character")) {
			return (char) guess == this.answer;
		} 
			return false;
		
	}

	/*
	 * TODO MCQ.5.0: toString() override: A String representation of a Multiple
	 * Choice question object will look like this (the actual number of options
	 * could vary): #1: Which of the following is NOT a variety of grape used
	 * for white wine? a) Riesling b) Malbec c) Sauvingnon Blanc d)
	 * Gewürztraminer e) Chenin Blanc
	 * 
	 * Make sure there are elements in the options data member (it will be
	 * null). If the options member has not been assigned options yet, use the
	 * text "No options added, yet!" instead of any actual multiple choice
	 * options.
	 * 
	 * Don't forget the Javadocs and DON'T use the specs above in the Javadocs:
	 * write friendly and professional docs in your own words.
	 * 
	 * HINT: you can increment a char and it will go to the next letter in the
	 * alphabet e.g. char letter = 'a'; letter++ will store 'b' in letter.
	 */
	public String toString() {

		StringBuffer finalText = new StringBuffer();
		char letter = 'a';
		finalText.append(this.getQuestionText());
		finalText.append("\n");
		if (options.length == 0)
			finalText.append("No options added, yet!");
		else {
			for(int iOption= 0; iOption< options.length; iOption++){
			    finalText.append(letter++);
				finalText.append(") ");
				finalText.append(options[iOption]);
				finalText.append("\n");
				
			}
			finalText.append("Answer: ");
		}
		return "#" + this.getQuetionId() + ": " + finalText;
	}
}
