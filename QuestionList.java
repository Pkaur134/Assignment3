package Final;
import java.util.ArrayList;

public class QuestionList extends ArrayList<Question>{

	public QuestionList() {
		
	}
	
	public QuestionList(ArrayList<Question> question) {	
		if(question.size() > 0) {
			for(int i=0;i<question.size();i++) {
			this.add(question.get(i));
			}
		}
	}
	
	public QuestionList(Question[] question) {	
		if(question.length > 0) {
			for(int i=0;i<question.length;i++) {
				this.add(question[i]);
			}
		}
	}
	public String presentQuestion(int index) {
		
		return null;
	}
}
