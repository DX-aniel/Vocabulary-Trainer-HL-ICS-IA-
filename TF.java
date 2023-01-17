import java.io.*;
import java.util.*;

public class TF{
	private String word; 
	private String definition;
	private boolean answer;
	public TF(String word, String definition, boolean answer){
		this.word = word;
		this.definition = definition;
		this.answer = answer;
	}
	public String getWord(){
		return word;
	}
	public String getDefinition(){
		return definition;
	}
	public boolean getAnswer(){
		return answer;
	}
}