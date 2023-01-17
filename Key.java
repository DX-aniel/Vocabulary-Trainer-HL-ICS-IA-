/******************************************************

Title: HL English Vocabulary Trainer
Author: Daniel Zhang
Class: ICS4U7

******************************************************/

import java.io.*;
import java.util.*;

public class Key{
	private String key;
	
	/**
	 * constructor for the key class
	 */
	
	public Key(String key){
		this.key = key;
	}
	
	/**
	 * @return the string value of the value object
	 * @overload 
	 */
	
	public String toString(){
		return key;
	}
	
	/**
	 * checks if 2 keys are equal, whether theyre the same instance of contain the same string
	 * @params k the value
	 * @return whether or not theyre equal
	 * @overload 
	 */
	
	public boolean equals(Key k){
		if (this == k){
			return true;
		}
		else{
			return Objects.equals(key, k.toString());
		}
	}
	
	/**
	 * @return java's hash code for the string of this key
	 */
	
	public int getHashCode(){
		return Objects.hashCode(key);
	}
}
