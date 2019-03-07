package JaskiratSingh_LIBa3;
import java.util.ArrayList;

public class QuestionList extends ArrayList<Question>{
	
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
		int size = this.size();
		if(index < size && index >= 0){
			return this.get(index).toString();
		}else{
			return null;
		}
	}

}
