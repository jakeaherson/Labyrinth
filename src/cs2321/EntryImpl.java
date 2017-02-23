package cs2321;

import net.datastructures.Entry;

public class EntryImpl<K, V> implements Entry<K,V> {
	private K key;
	private V value;

	public EntryImpl(K k, V v) {
		key = k;
		value = v;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	

	public String toString() {
		return "" + key;
	}

}
