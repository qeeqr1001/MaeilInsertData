package insert;
import java.util.ArrayList;

public class DTO {
	String quest_id;
	String quest_content;
	String quest_keyword;
	String ans_content;
	int Word_id;
	String word_content;
	String[] wordf;
	public static ArrayList<String> wordfdic = new ArrayList<String>();		
	
	public String getId() {
		return quest_id;
	}
	public void setId(String quest_id) {
		this.quest_id=quest_id;
	}
	public String getText() {
		return ans_content;
		
	}
	public void setText(String ans_content) {
		this.ans_content=ans_content;
	}
	
	public String getContent() {
		return quest_content;
	
	}
	public void setContent(String quest_content) {
		this.quest_content=quest_content;
	}
	public int getWordId() {
		
		return Word_id;
	}
	public void setWordId(int Word_id) {
		this.Word_id=Word_id;
	}
	public String getWord() {
		return word_content;
	
	}
	public void setWord(String word_content) {
		this.word_content=word_content;
	}
	
	

}
