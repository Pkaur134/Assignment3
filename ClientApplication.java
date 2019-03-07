package Final;
/*
 * File updated on March 3, 2018 by M. Mohiuddin to reduce ToDO list
 Name:  [Jaskirat Singh]
 Assignment:  Assignment 3
 
 Description:
 [program description in your own words]
 */
import java.util.Scanner;
import prog24178.util.Validator;
/* note: The above import is for the Validator jar file that is provided to you and that you must add to the library */

public class ClientApplication {

    // Scanner used for all keyboard input
    private static Scanner in = new Scanner(System.in);
    // validator used for all data validation
    private static Validator validator = new Validator();

    public static void main(String[] args) {

        // construct a new QuestionList object to contain all of the
        // questions we add
        QuestionList list = new QuestionList();
        
        // initialize option for main menu
        char option = '\0';
        
        // display main menu until user decides to quit
        do {
            // display the main menu of options
            displayMenu();
            // get the user's choice
            String strOption = in.nextLine();
            // make sure they didn't press enter without typing anything
            if (!strOption.isEmpty()) {
                // get only the first character as upper-case letter
                option = strOption.toUpperCase().charAt(0);
            }
            
            // take action, based on user's option choice
            switch (option) {
                
                case 'A': // add a question to the list
                    
                    // ask the user what type of question they want to
                    // add and get their choice of type
                    /* TODO 1.0: you need to write some of the
                    getQuestionType() method code so go there (next task). */
                    QuestionType type = getQuestionType();
                    
                    // get a new question of this type
                    /* TODO 2.0:  you need to write some of the code in the
                    getQuestion() method so go there (next task). */
                    Question q = getQuestion(type);
                    
                    // if the question is not empty, add it to the list
                    if (q != null) {
                        // TODO 3: add this question to the list
                    	list.add(q);
                        
                    }
                    break;
                    
                case 'Q': // do the question list as a quiz
                    // TODO 4.0: write this method (see task 4a below)
                    serveQuiz(list);
                    break;
                    
                case 'X': // exit, don't do anything here
                    break;
                    
                default: // invalid option
                    System.out.println("Invalid option.");
            }
        // loop continues as long as user doesn't choose to exit
        } while (option != 'X');
        
        // bye
        System.out.println("Exiting Program...");

    }

    /*
     * Displays the main menu.
     */
    public static void displayMenu() {
        System.out.println("Choose an Option:");
        System.out.println("(A)dd a a Question");
        System.out.println("Do a (Q)uiz");
        System.out.println("E(x)it Program");
        System.out.print("> ");
    }

    /*
     * Gets a user's choice of question type as a QuestionType enum
     * constant.
     */
    public static QuestionType getQuestionType() {
        
        // init question type entered by user as something invalid
        int questionType = -1;
      //  questionTypeValue.getType();
        // loop as long as the question type is valid
        do {
            // display the question types menu (this was one of your
            // previous tasks)
            
            displayTypes(); 
            
            // get the user's choice and set up a validator for it
            String strType = in.nextLine();
            validator.setValue(strType);
            
            // as long as the user's choice is not numeric, keep asking
            while (!validator.isValidInteger()) {
                // error message
                System.out.println("Invalid choice. Please try again.");
                // display menu again
                displayTypes();
                // ask user for new value and reset validator value
                strType = in.nextLine();
                validator.setValue(strType);
            }
            // if we get here, the question type must be valid, so parse it
            questionType = Integer.parseInt(strType);
            
        // continue as long as the question type is a valid QuestionType ordinal
        } while (!Validator.isValidRange(questionType,
                new int[]{1, QuestionType.values().length},
                new boolean[]{true, true}));

        // TODO 1.2: return a QuestionType enum value for the user's choice
        if(questionType == 1)
        	return QuestionType.TRUE_FALSE;
        else if(questionType == 2)
        	return QuestionType.SHORT_ANSWER;
        else 
        	return QuestionType.MULTIPLE_CHOICE;
    }

        /*
     * Displays the menu of question types to choose from.
     * 
     */
    public static void displayTypes() {
        // display the prompt
        System.out.println("Choose a question type by number:");
        
        /* TODO 1.1: For each question type constant in the QuestionType enum,
           print a menu of options.  It should appear like this:
       Choose a question type by number:
       1. True/False
       2. Short Answer
       3. Multiple-Choice
       >   
        */
        int number = 1;
        for(QuestionType value : QuestionType.values()) {
        	System.out.println(number + ". " + value.getType());
        	number++;
        	}
        
        // display a little prompt
        System.out.print("> ");
    }

    /*
     * Gets a question object of a specific type with user input.
     * 
     */
    public static Question getQuestion(QuestionType type) {

        Question q = null; // init a question object
        
        // as long as the question is empty, keep asking user for the
        // info to create the question object
        while (q == null) {
            
            try {
                // get the question id
                int id = getIntegerValue("Question ID: ");
                while (!Validator.isValidRange(id, 0, true, false)) {
                    System.out.println("Invalid value for ID. Please try again.");
                    id = getIntegerValue("Question ID: ");
                }

                // get the question text
                String qText = getNonEmptyString("Question text: ");

                /* TODO 2.1: use a switch or if-else:
                   Based on the QuestionType variable type, get the question 
                   answer of an appropriate type and constsruct a TFQuestion, 
                   SAQuestion, or MCQuestion.  For MCQuestion, you'll also
                   have to get the list of available options using the
                   getMcOptions() method (the next task) before you get 
                   the question's correct answer.*/
                	String questionType = type.name();
                	String answer ;
                	
                	if(questionType.equalsIgnoreCase((QuestionType.TRUE_FALSE).name())) {
                		answer = getNonEmptyString("Answer: ");
                		boolean ans = false;
                		if(answer instanceof String && (answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("false"))) {
                			ans = Boolean.parseBoolean(answer);
                		}
                		else { // invalid answer - throw exception
                			throw new IllegalArgumentException(answer + " is not a valid answer option and its should be either true or false.");
                		}
                		TFQuestion tfQuestion = new TFQuestion(id, qText, ans);
                		q = tfQuestion;
                		
                	}
                	else if(questionType.equalsIgnoreCase((QuestionType.SHORT_ANSWER).name())) {
                		answer = getNonEmptyString("Answer: ");
                		SAQuestion saQuestion = new SAQuestion(id, qText, answer);
                		q = saQuestion;
                	}
                	else if(questionType.equalsIgnoreCase((QuestionType.MULTIPLE_CHOICE).name())){
                		char ans = 0;
                		String[] options = getMcOptions();
                		answer = getNonEmptyString("Answer: ");
                		if(answer instanceof String)
                		{
                			ans = answer.charAt(0);
                		}
                		MCQuestion mcQuestion = new MCQuestion(id, qText, ans, options);
                		q = mcQuestion;
                	}
                	
                
                // blank line to make things look nice
                System.out.println();

            } catch (Exception ex) {
                // to catch IllegalArgumentException's thrown from any of the 
                // Question classes
                System.out.println(ex.getMessage());
                System.out.println("Sorry, you need to start over...");
            }
        }
        
        // return the Question object we just constructed
        return q;
    }

    /*
     * Gets an array of multiple-choice options for the MCQuestion class.
     * TODO 2.2: Fill in the missing code.
     */
    public static String[] getMcOptions() {
        
        // init number of options user wants
        int numOptions = 0;
        
        // loop as long as they enter a valid number of options
        do {
            // get number of options from the user
            numOptions = getIntegerValue("How many options does your question have?");
        } while (!Validator.isValidRange(numOptions, 0, true, false));
        
        // the array that will contain the multiple choice options
        // TODO 2.3: construct a String array with numOptions elements
        String[] options = new String[numOptions];

        // get the multiple choice options and store in the array
        System.out.println("Enter the Multiple Choice Options:");
        // for each option the question needs:
        for (int i = 0; i < numOptions; i++) {
            
            // this is the string used to prompt the user - displays
            // a), b), c), etc.  Cool, eh?  97 should probably be a constant :P
            String prompt = "Option " + (char) (i + 97) + ")";
            
            /* TODO 2.4: use my getNonEmptyString() method to get an option
            from the user and store it in the current element of the
            String options array.*/
            options[i] = getNonEmptyString(prompt);
            
        }

        // TODO 2.5: return the string array of multiple-choice options
        return options;
        
    }

    /*
     * This method accepts a QuestionList and presents them as a quiz to
     * the user.  The score is displayed after all the questions have been
     * answered.  If the QuestionList is empty, the message "Sorry, no questions
     * to give you.  Add some!" is displayed.
     * TODO 4.1: Write the code for this method.
     */
    public static void serveQuiz(QuestionList list) {
        // If the list is empty, display the "Sorry, no questions.." message.
        // otherwise, use a loop to iterate through the QuestionList and
        // QuestionList.presentQuestion() each question to the user.
        // Keep track of the score and display it at the end e.g. 3/5.
        // Use the methods in the Questions and QuestionList classes whenever
        // appropriate.
    	if(!list.isEmpty()) {
    		Question q = null;
    		boolean correctAnswer;
    		int score = 0;
    		int totalQuestions = list.size();
    		for(int i=0;i<list.size();i++) {
    			String question = list.presentQuestion(i);
    			System.out.println(question);
    			String userAnswer = getNonEmptyString("Answer: ");
    			System.out.println();
    			q = list.get(i);
    			if(q instanceof MCQuestion) {
    				correctAnswer = q.isCorrect(userAnswer);
    				if(correctAnswer)
    					score++;
    			}
    			if(q instanceof SAQuestion) {
    				correctAnswer = q.isCorrect(userAnswer);
    				if(correctAnswer)
    					score++;
    			}
    			if(q instanceof TFQuestion) {
    				correctAnswer = q.isCorrect(userAnswer);
    				if(correctAnswer)
    					score++;
    			}
    		}
    		System.out.println();
    		System.out.println("Your Score: " + score + "/" + totalQuestions);
    		System.out.println();
    	}
    	else {
    		System.out.println("Sorry, no questions..");
    	}

    }
    
    /*
     * A helper method that gets an integer value from the user.
     * IF YOU COPY THIS CODE FOR YOUR OWN ASSIGNMENTS, YOU MUST
     * DOCUMENT IT IN FULL IN YOUR OWN WORDS, OTHERWISE YOU'LL BE BREACHING 
     * THE ACADEMIC INTEGRITY POLICY, WHICH CAN RESULT IN A 0 GRADE,
     * TERMINATION FROM THE COURSE, OR EXPLUSION FROM THE COLLEGE.
     * IF YOU USE THIS CODE, DOCUMENT IT IN FULL IN YOUR OWN WORDS AND 
     * INDICIATE WHICH FILE/EXAMPLE/ETC YOU GOT IT FROM.
     */
    private static int getIntegerValue(String prompt) {
        System.out.print(prompt + " ");
        String strValue = in.nextLine();
        validator.setValue(strValue);
        while (!validator.isValidInteger()) {
            System.out.println("Invalid value: please enter only digits.");
            System.out.print(prompt + " ");
            strValue = in.nextLine();
            validator.setValue(strValue);
        }
        return Integer.parseInt(strValue);
    }

    /*
     * A helper method used to get a non-empty string from the user.
     * IF YOU COPY THIS CODE FOR YOUR OWN ASSIGNMENTS, YOU MUST
     * DOCUMENT IT IN FULL IN YOUR OWN WORDS, OTHERWISE YOU'LL BE BREACHING 
     * THE ACADEMIC INTEGRITY POLICY, WHICH CAN RESULT IN A 0 GRADE,
     * TERMINATION FROM THE COURSE, OR EXPLUSION FROM THE COLLEGE.
     * IF YOU USE THIS CODE, DOCUMENT IT IN FULL IN YOUR OWN WORDS AND 
     * INDICIATE WHICH FILE/EXAMPLE/ETC YOU GOT IT FROM.
    */
    private static String getNonEmptyString(String prompt) {
        System.out.print(prompt + " ");
        String text = in.nextLine();
        while (text.isEmpty()) {
            System.out.println("Error: Field can't be blank.");
            System.out.print(prompt + " ");
            text = in.nextLine();
        }
        return text;
    }

}
