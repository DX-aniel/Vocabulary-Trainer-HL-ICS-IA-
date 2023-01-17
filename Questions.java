import java.util.*;
import java.io.*;

public class Questions{
	private Hashmap hm;
	private String[] keys; //for each hashmapdatabase there will be an array containing the keys so that the index of the keys can be easily accessed and randomized
	private ArrayList<String> learnedKeys;
	private Accounts acc;
	private int max;
	private int learnedMax;
	private int index;
	private Key k;
	private String word;
	
	/**
	 * constructor for the Questions class
	 * @param hm the hashmap with all the words and definitions
	 * @param keys array with all the unlearned words
	 * @param learnedKeys arraylist with all the learned words
	 * @param acc the account that is testing
	 */
	
	public Questions(Hashmap hm, String[] keys, ArrayList<String> learnedKeys, Accounts acc){
		this.hm = hm;
		this.keys = keys;
		this.learnedKeys = learnedKeys;
		this.acc = acc;
	}

	/**
	 * generates a word for the user to learn
	 * @return a word for the user to learn that hasnt been learned
	 */

	public String learn(){
		max = keys.length;
		index = (int) (Math.random()*max);
		acc.learnedWord(keys[index]);
		return keys[index];
	}
	
	/**
	 * generates a multiple choice question
	 * @return data for the question in the form of an instance of MC
	 */
	
	public MC mc(){
		learnedMax = learnedKeys.size();
		max = keys.length;
		index = (int) (Math.random()*learnedMax);
		word = learnedKeys.get(index);
		k = new Key(word);
		
		String[] definitions = new String[4];
		int answer = (int)(Math.random() * 4);
		
		for(int i = 0; i < 4; i++){
			if(i == answer){
				definitions[i] = hm.get(k);
			}
			else{
				int index2 = (int) (Math.random()*max);
				Key k2 = new Key(keys[index2]);
				definitions[i] = hm.get(k2);
			}
		}
		
		MC mc = new MC (word, definitions, answer + 1);
		return mc;
	}
	
	/**
	 * generates a matching question
	 * @return data for the question in the form of an instance of match
	 */

	public Match match(){
		String[] words = new String[4];
		String[] words2 = new String[4];
		max = keys.length;
		learnedMax = learnedKeys.size();
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < learnedMax; i++) {
            temp.add(i);
        }
        Collections.shuffle(temp);
		
		for(int i = 0; i < 4; i++){
			index = temp.get(i);
			words[i] = learnedKeys.get(index);
			words2[i] = learnedKeys.get(index);
		}
		
		Collections.shuffle(Arrays.asList(words2));
		
		k = new Key(words2[0]);
		Key k2 = new Key(words2[1]);
		Key k3 = new Key(words2[2]);
		Key k4 = new Key(words2[3]);
		
		String[] definitions = new String[4];
		
		definitions[0] = hm.get(k);
		definitions[1] = hm.get(k2);
		definitions[2] = hm.get(k3);
		definitions[3] = hm.get(k4);
		
		int[] answers = new int[4];
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if (words2[i].equals(words[j])){
					answers[i] = j + 1;
				}
			}
		}
		
		Match match = new Match(words, definitions, answers);
		return match;
	}
	
	/**
	 * generates a true/false question
	 * @return data for the question in the form of an instance of TF
	 */
	
	public TF tf(){ 
		learnedMax = learnedKeys.size();
		max = keys.length;
		index = (int) (Math.random()*learnedMax);
		word = learnedKeys.get(index);
		k = new Key(learnedKeys.get(index));
		TF tf;
		if(Math.random() < 0.5){
			tf = new TF(learnedKeys.get(index), hm.get(k), true);
		}
		else{
			int index2 = (int) (Math.random()*max);
			Key k2 = new Key(keys[index2]);
			tf = new TF(learnedKeys.get(index), hm.get(k2), false);
		}
		return tf;
	}
}