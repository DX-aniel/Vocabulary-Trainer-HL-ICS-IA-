import java.io.*;
import java.util.*;

public class Match{
	private String[] words; 
	private String[] definitions;
	private int[] answers;
	public Match(String[] words, String[] definitions, int[] answers){
		this.words = words;
		this.definitions = definitions;
		this.answers = answers;
	}
	public String getWord1(){
		return words[0];
	}
	public String getWord2(){
		return words[1];
	}
	public String getWord3(){
		return words[2];
	}
	public String getWord4(){
		return words[3];
	}
	public String[] getWords(){
		return words;
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
	public int[] getAnswer(){
		return answers;
	}
}