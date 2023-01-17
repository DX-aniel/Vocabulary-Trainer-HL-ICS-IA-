/******************************************************

Title: HL English Vocabulary Trainer
Author: Daniel Zhang
Class: ICS4U7

******************************************************/

import java.io.*;
import java.util.*;

public class HashNode{
	private Key key;
	private Value value;
	private HashNode next;
	
	/**
	 * constructor for the hashnode class
	 */
	
	public HashNode(Key k, Value v){
		key = k;
		value = v;
	}
	
	/**
	 * @return the key of the hashnode
	 */
	
	public Key getKey(){
		return key;
	}
	
	/**
	 * @return the value of the hashnode
	 */
	
	public Value getValue(){
		return value;
	}
	
	/**
	 * sets the value of the hashnode
	 * @param v the value being changed into
	 */
	
	public void setValue(Value v){
		value = v;
	}
	
	/**
	 * @return the hashnode after it in a linkedlist
	 */
	
	public HashNode getNext(){
		return next;
	}
	
	/**
	 * setting the hashnode that follows this one in a linkedlist
	 * @param hn the hashnode after it 
	 */
	
	public void setNext(HashNode hn){
		next = hn;
	}
}