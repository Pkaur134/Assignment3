package Final;
import java.util.ArrayList;

public class QuestionList extends ArrayList<Question>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// default Constructor
	QuestionList(){
		
	}
	
	// Constructor for setting questions in QuestionList
	QuestionList(Question[] q){
		for(Question question: q){
			this.add(question);
		}
		
	}
	/*Return the Question*/ 
	public String presentQuestion(int index){
		if(index<this.size() && index>0){
			return this.get(index).toString();
		}else{
			return null;
		}
	}

}
