import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;

public class Hashmap{
	private ArrayList <HashNode> bucketArray;
	private int bucketCapacity;
	private int size;
	private int index;
	private int hashCode;
	private HashNode head;
	private HashNode prev;
	
	/**
	 * Hashmap constructor
	 */
	
	public Hashmap(){
		bucketArray = new ArrayList<>();
		bucketCapacity = 10;
        size = 0;
		
		for (int i = 0; i < bucketCapacity; i++){
			bucketArray.add(null);
		}
		
	}
	
	/**
	 * @params key the key that the index is being calculated for
	 * @return the index of the bucket the key is in
	 */
	
	private int getIndex(Key key){
		hashCode = key.getHashCode();
		index = hashCode % bucketCapacity;
		return Math.abs(index);
	}
	
	/**
	 * @params key the key that the value is being returned for
	 * @return the string of the value that is paired with this key
	 */
	
	public String get(Key key){
		index = getIndex(key);
		hashCode = key.getHashCode();
		head = bucketArray.get(index);
		
		while (head != null){
			if((head.getKey()).equals(key) && hashCode == ((head.getKey()).getHashCode())){
				return (head.getValue()).toString();
			}
			else{
				head = head.getNext();
			}
		}
		
		return null;
	}
	
	/**
	 * adds a key pair value to the hashmap
	 * @params key the key that is being added
	 * @params value the value that is being added
	 */
	
	public void add(Key key, Value value){
		index = getIndex(key);
		hashCode = key.getHashCode();
		head = bucketArray.get(index);
		
		while (head != null) {
            if ((head.getKey()).equals(key) && hashCode == (head.getKey()).getHashCode()) {
                head.setValue(value);
                break;
            }
			else{
				head = head.getNext();
			}
        }
		
		head = bucketArray.get(index);
		HashNode newNode = new HashNode(key, value);
		newNode.setNext(head);
		bucketArray.set(index, newNode);
		
		if ((double)(size/bucketCapacity) >= 0.7){
			ArrayList<HashNode> temp = bucketArray;
            bucketArray = new ArrayList<>();
            bucketCapacity *= 2;
            size = 0;
            for (int i = 0; i < bucketCapacity; i++){
                bucketArray.add(null);
			}
		
            for (HashNode headNode : temp){
                while (headNode != null){
                    add(headNode.getKey(), headNode.getValue());
                    headNode = headNode.getNext();
                }
            }
        }
	}
	
	/**
	 * @return the size of the hashmap
	 */
	
	public int size(){
		return size;
	}
	
	/**
	 * @return whether or not the hashmap is empty
	 */
	
	public boolean isEmpty(){
		if (size == 0){
			return true;
		}
		else{
			return false;
		}
	}
}
