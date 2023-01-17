import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;
import org.json.simple.JSONObject.*;
import java.nio.file.Path;

public class Accounts{
	
	private String user;
	private JSONObject stats;
	private JSONObject learned;
	private JSONParser parser;
	
	/**
	 * constructor for the Accounts class
	 * @param user the username of the account
	 * @param learnedString the string of a JSONObject containing the learned words
	 */
	
	public Accounts(String user, String learnedString){
		this.user = user;
		parser = new JSONParser();
		try{
			stats = (JSONObject) parser.parse(new FileReader(user + ".json"));
			learned = (JSONObject) parser.parse(learnedString);
		}
		catch(Exception e){
		}
	}
	
	/**
	 * creates a new account by adding the information to AccountInfo.txt
	 * @param user the username of the account
	 * @param pass the password of the account
	 * @return whether or not the account was created
	 */
	
	public static boolean register(String user, String pass){
		if(check(user)){
			try{			
				FileWriter fw = new FileWriter("AccountInfo.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(user + ", " + pass + "\n");
				bw.close();
				fw.close();
				newAcc(user);
			}
			catch (Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}
		else{
			System.out.println("test");
			return false;
		}
	}
	
	/**
	 * checks if the inputted username already exists
	 * @param user the username of the account
	 * @return whether or not the username exists
	 */
	
	public static boolean check(String user){
		try{
			BufferedReader br = new BufferedReader(new FileReader("AccountInfo.txt"));
			String temp;
			String [] tempArr;
			while ((temp = br.readLine()) != null){
				tempArr = temp.split(",");
				System.out.println(tempArr[0]);
				if (tempArr[0].equals(user)){
					return false;
				}
			}
			br.close();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * checks if the username and password are correct and exist
	 * @param user the username of the account
	 * @param pass the password of the account
	 * @return whether or not the username and password are correct
	 */
	
	public static boolean check(String user, String pass){
		try{
			BufferedReader br = new BufferedReader(new FileReader("AccountInfo.txt"));
			String temp;
			String [] tempArr;
			while ((temp = br.readLine()) != null){
				tempArr = temp.split(", ");
				if (tempArr[0].equals(user) && tempArr[1].equals(pass)){
					return true;
				}
			}
			br.close();
			return false;
		}
		catch (Exception e){
			return false;
		}
	}
	
	/**
	 * creates the JSON files to store account related information
	 * @param user the username of the account
	 */
	
	public static void newAcc(String user){
		JSONObject stats = new JSONObject();
		stats.put("Words Learned", "0");
		stats.put("Words Mastered", "0");
		stats.put("Correct Answers", "0");
		
		try{
			FileWriter fw = new FileWriter(user + ".json");
			fw.write(stats.toJSONString());
			fw.close();
			
		    fw = new FileWriter(user + "Learned.json");
			JSONObject temp = new JSONObject();
			temp.put("a", "0");
			fw.write(temp.toJSONString());
			fw.close();
			
		} 
		catch (Exception e) {
		}
	}

	/**
	 * getter method
	 * @return the account statistics
	 */

	public String[] getStats(){
		String[] out = new String[3];
		out[0] = (String) stats.get("Words Learned");
		out[1] = (String) stats.get("Words Mastered");
		out[2] = (String) stats.get("Correct Answers");
		return out;
	}
	
	/**
	 * adds a word to the learned words arraylist and JSON file while increasing the number of words learned in statistics
	 * @param word the word being learned
	 */
	
	public void learnedWord(String word){
		try{
			FileWriter fw = new FileWriter(user + "Learned.json");
			learned.put(word, "0");
			fw.write(learned.toJSONString());
            fw.close();
			
			int wordsLearned = Integer.parseInt((String)stats.get("Words Learned"));
			wordsLearned++;
			FileReader fr = new FileReader(user + ".json");
			parser = new JSONParser();
			stats = (JSONObject) parser.parse(fr);
			stats.put("Words Learned", String.valueOf(wordsLearned));
			FileWriter file = new FileWriter(user + ".json");
			file.write(stats.toJSONString());
			file.close();
			fr.close();
			GUI.updateLearned();
		}
		catch (Exception e){ 
			try{
				e.printStackTrace();
				learned = new JSONObject();
				learned.put(word, "0");
				FileWriter fw = new FileWriter(user + "Learned.json");
				fw.write(learned.toJSONString());
				fw.close();
			}
			catch(Exception ex){
			}
			
		}
	}
	
	/**
	 * increases the number of mastery for a word
	 * @param word the word that will have its mastery increased
	 */
	
	public void addMastery(String word){
		int temp = Integer.parseInt((String) learned.get(word));
		temp++;
		learned.put(word, String.valueOf(temp));
		if(temp == 10){
			addWordsMastered();
			learned.remove(word);
		}
		addCorrectAnswers();
		try{
			FileWriter fw = new FileWriter(user + "Learned.json");
			fw.write(learned.toJSONString());
			fw.close();
		}
		catch(Exception e){
		}
	}
	
	/**
	 * increases the number of mastery for words
	 * @param words[] the words that will have its mastery increased
	 */
	
	public void addMastery(String[] words){
		for(int i = 0; i < 4; i++){
			int temp = Integer.parseInt((String) learned.get(words[i]));
			temp++;
			learned.put(words[i], String.valueOf(temp));
			addCorrectAnswers();
			if(temp == 10){
				addWordsMastered();
				learned.remove(words[i]);
			}
		}
		try{
			FileWriter fw = new FileWriter(user + "Learned.json");
			fw.write(learned.toJSONString());
			fw.close();
		}
		catch(Exception e){
		}
	}
	
	/**
	 * increases the number of mastery for words
	 * @param words[] the words that will have its mastery increased
	 * @params answers[] the words that were answered right or wrong
	 */
	
	public void addMastery(String[] words, boolean[] answers){
		for(int i = 0; i < 4; i++){
			if(answers[i]){
				int temp = Integer.parseInt((String) learned.get(words[i]));
				temp++;
				learned.put(words[i], String.valueOf(temp));
				addCorrectAnswers();
				if(temp == 10){
					addWordsMastered();
					learned.remove(words[i]);
				}
			}
		}
		try{
			FileWriter fw = new FileWriter(user + "Learned.json");
			fw.write(learned.toJSONString());
			fw.close();
		}
		catch(Exception e){
		}
	}
	
	/**
	 * increases the number of words mastered and updates the JSON file
	 */
	
	public void addWordsMastered(){
		int wordsMastered = Integer.parseInt((String)stats.get("Words Mastered"));
		wordsMastered++;
		try{
			FileReader fr = new FileReader(user + ".json");
			parser = new JSONParser();
			stats = (JSONObject) parser.parse(fr);
			stats.put("Words Mastered", String.valueOf(wordsMastered));
			FileWriter file = new FileWriter(user + ".json");
			file.write(stats.toJSONString());
			file.close();
			fr.close();
		}
		catch(Exception e){
		}
	}
	
	/**
	 * increases the number of correct answers and updates the JSON file
	 */
	
	public void addCorrectAnswers(){
		int correctAnswers = Integer.parseInt((String)stats.get("Correct Answers"));
		correctAnswers++;
		try{
			FileReader fr = new FileReader(user + ".json");
			parser = new JSONParser();
			stats = (JSONObject) parser.parse(fr);
			stats.put("Correct Answers", String.valueOf(correctAnswers));
			FileWriter file = new FileWriter(user + ".json");
			file.write(stats.toJSONString());
			file.close();
			fr.close();
		}
		catch(Exception e){
		}
	}
	

}