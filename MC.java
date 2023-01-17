import java.io.*;
import java.util.*;

public class MC{
	private String word; 
	private String[] definitions;
	private int answer;
	public MC(String word, String[] definitions, int answer){
		this.word = word;
		this.definitions = definitions;
		this.answer = answer;
	}
	public String getWord(){
		return word;
	}
	public String getDefinition1(){
		return definitions[0];
	}
	public String getDefinition2(){
		return definitions[1];
	}
	public String getDefinition3(){
		return definitions[2];
	}
	public String getDefinition4(){
		return definitions[3];
	}
	public int getAnswer(){
		return answer;
	}
}