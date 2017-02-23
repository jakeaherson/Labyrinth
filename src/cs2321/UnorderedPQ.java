package cs2321;

import java.util.ArrayList;
import java.util.Comparator;

import net.datastructures.DefaultComparator;
import net.datastructures.EmptyPriorityQueueException;
import net.datastructures.Entry;
import net.datastructures.MaxPriorityQueue;
/**
 * A PriorityQueue based on an Unordered Sequence. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author jake herson
 */

public class UnorderedPQ<K extends Comparable<K>,V> implements MaxPriorityQueue<K,V>  {

	protected ArrayList<Entry<K, V>> theList;
	private Comparator<K> compar = new DefaultComparator<K>();
	private int size = 0;


	// Constructor with default comparator
	public UnorderedPQ() {
		theList = new ArrayList<Entry<K, V>>();
	}
	/**
	 * Inserts using add First the elements to the PQ
	 * 
	 * @param key the Key that is going into the priority queue
	 * @param value the Value that is being insterted at a key in the PQ
	 * 
	 * @return The Entry of whats is being inserted
	 * 
	 * @throws InvalidKeyException
	 * 
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		//E entryimp = new EntryImpl<K, V>(key, value);
		try {
			theList.add(new EntryImpl<K, V>(key, value));
			size++;

		} catch (Exception e) {
			throw new InvalidKeyException(
					"I can't use these two things together.");
		}


		return  new EntryImpl<K, V>(key, value);
	}
	/**
	 * Returns true if PQ is empty false other wise
	 * 
	 * @return Boolean - empty or not
	 * 
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	public boolean isEmpty() {
		return theList.isEmpty();
	}

	/**
	 * Compares the elements in the PQ and returns the max
	 * 
	 * @return The Entry of whats is being inserted
	 * 
	 * @throws EmptyPriorityQueueException
	 * 
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(1)")
	@TimeComplexityAmortized("O(n)")
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		if(size == 0){
			throw new EmptyPriorityQueueException
			("the PQ is empty");
		}
		int newSize = 1;
		Entry<K,V> max = theList.get(0);
		//System.out.println(max.getValue());
		int i = 0;
		while(i < size-1){
			if(compar.compare(max.getKey(),theList.get(newSize).getKey()) >= 0){


				//	System.out.println(compar.compare(theList.get(newSize).getKey(),max.getKey()) > 0);
				max = theList.get(newSize);
			}
			newSize++;
			i++;
		}
		return max;

	}
	/**
	 * Compares the values in the PQ to find Max, and removes it 
	 * 
	 * @return The Entry being removed
	 * 
	 * @throws EmptyPriorityQueueException
	 * 
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(1)")
	@TimeComplexityAmortized("O(n)")
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		if(size == 0){
			throw new EmptyPriorityQueueException
			("the PQ is empty");
		}

		int newSize = 1;
		Entry<K,V> max = theList.get(0);
		//System.out.println(max.getValue());
		int i = 0;
		int store = 0;
		while(i < size-1){
			if(compar.compare(max.getKey(),theList.get(newSize).getKey()) > 0){


				//	System.out.println(compar.compare(theList.get(newSize).getKey(),max.getKey()) > 0);
				max = theList.get(newSize);
				store = newSize;
			}
			newSize++;
			i++;
		}
		theList.remove(store);
		size--;
		return max;
	}
	public Entry<K,V> max() throws EmptyPriorityQueueException {
		if(size == 0){
			throw new EmptyPriorityQueueException
			("the PQ is empty");
		}
		int newSize = 1;
		Entry<K,V> max = theList.get(0);
		//System.out.println(max.getValue());
		int i = 0;
		while(i < size-1){
			if(compar.compare(max.getKey(),theList.get(newSize).getKey()) <= 0){


				//	System.out.println(compar.compare(theList.get(newSize).getKey(),max.getKey()) > 0);
				max = theList.get(newSize);
			}
			newSize++;
			i++;
		}
		return max;

	}
	/**
	 * Compares the values in the PQ to find Max, and removes it 
	 * 
	 * @return The Entry being removed
	 * 
	 * @throws EmptyPriorityQueueException
	 * 
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(1)")
	@TimeComplexityAmortized("O(n)")
	public Entry<K,V> removeMax() throws EmptyPriorityQueueException {
		if(size == 0){
			throw new EmptyPriorityQueueException
			("the PQ is empty");
		}

		int newSize = 1;
		Entry<K,V> max = theList.get(0);
		//System.out.println(max.getValue());
		int i = 0;
		int store = 0;
		while(i < size-1){
			if(compar.compare(max.getKey(),theList.get(newSize).getKey()) < 0){


				//	System.out.println(compar.compare(theList.get(newSize).getKey(),max.getKey()) > 0);
				max = theList.get(newSize);
				store = newSize;
			}
			newSize++;
			i++;
		}
		theList.remove(store);
		size--;
		return max;
	}
	/**
	 * Returns the size of the list
	 * 
	 * @return the size
	 * 
	 * 
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	public int size() {
		return theList.size();
	}

}
