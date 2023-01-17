/******************************************************

Title: HL English Vocabulary Trainer
Author: Daniel Zhang
Class: ICS4U7

******************************************************/

import java.io.*;
import java.util.*;

public class Value{
	private String value;
		
	/**
	 * constructor for the value class
	 */
	
	public Value(String value){
		this.value = value;
	}
		
	/**
	 * checks if 2 values are equal, whether theyre the same instance of contain the same string
	 * @params v the value
	 * @return whether or not theyre equal
	 * @overload 
	 */
		
	public boolean equals(Value v){
		if (this == v){
			return true;
		}	
		return Objects.equals(value, v.toString());
	}
	
	/**
	 * @return the string value of the value object
	 * @overload 
	 */
	
	public String toString(){
		return value;
	}
}
