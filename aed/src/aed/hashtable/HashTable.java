package aed.hashtable;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Arrays;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.InvalidKeyException;


/**
 * A hash table implementing using open addressing to handle key collisions.
 */
public class HashTable<K, V> implements Map<K, V> {
	Entry<K, V>[] buckets;
	int size;

	public HashTable(int initialSize) {
		this.buckets = createArray(initialSize);
		this.size = 0;
	}

	/**
	 * Add here the method necessary to implement the Map api, and any auxilliary
	 * methods you deem convient.
	 */

	// Examples of auxilliary methods: IT IS NOT REQUIRED TO IMPLEMENT THEM

	/**
	 * Creates a new entry array
	 * 
	 * @param int
	 * @return Entry<K,V>[]
	 */
	@SuppressWarnings("unchecked")
	private Entry<K, V>[] createArray(int size) {
		return new Entry[size];
	}

	/**
	 * Returns the bucket index of an object
	 * 
	 * @param Object
	 * @return int
	 */
	private int index(Object obj) {
		return obj.hashCode() % buckets.length;
	}

	/**
	 * Returns the index where an entry with the key is located, or if no such entry
	 * exists, the "next" bucket with no entry, or if all buckets stores an entry,
	 * -1 is returned.
	 * 
	 * @param Object
	 * @return int 
	 */
	private int search(Object key) {
		int res = -1;
		for (int indexer = 0; indexer < buckets.length; indexer++)
			if (buckets[indexer] != null && key.equals(buckets[indexer].getKey()))
				res = indexer;
		int length = buckets.length;
		int index = index(key);
		int indexer = index;
		while (indexer < length && res == -1 && indexer+1!=index) {
			indexer = indexer%length;
			if (buckets[indexer] == null||key.equals(buckets[indexer].getKey()))
				res = indexer;
			indexer++;
		}
		return res;
	}

	/**
	 * Doubles the size of the bucket array, and inserts all entries present in the
	 * old bucket array into the new bucket array, in their correct places. Remember
	 * that the index of an entry will likely change in the new array, as the size
	 * of the array changes.
	 */

	@SuppressWarnings("unchecked")
	private void rehash() {
		Entry<K, V>[] newBuckets = new Entry[2 * buckets.length];
		for (int i = 0; i < buckets.length; i++) {
			newBuckets[i] = buckets[i];
		}
		buckets = newBuckets;
	}

	/**
	 * Deletes the posible gaps generated when remove is called
	 * 
	 * @param int 
	 */
	
	private void colapse(int indexHueco) {
		int start = indexHueco;
		int length = buckets.length;
		int indexer=(indexHueco+1) % length;
		while(indexer!=start &&	buckets[indexer]!=null) {
			if(near(indexer, indexHueco, index(buckets[indexer].getKey()))) {
				buckets[indexHueco] = buckets[indexer];
				buckets[indexer] = null;
				indexHueco = indexer;
			}
			indexer = (indexer+1) % length;
		}

	}
	
	/**
	 * return true if the index is nearer indexHueco than indexPreferido
	 * 
	 * @param int 
	 * @param int 
	 * @param int 
	 * @return boolean
	 */
	
	private boolean near(int index, int indexHueco, int indexPreferido) {
		boolean mayorHueco = indexHueco < index;
		boolean menorPreferido = index < indexPreferido;
		return (!menorPreferido && indexPreferido<=indexHueco && mayorHueco) ||
				(menorPreferido && (indexHueco>=indexPreferido || mayorHueco));
	}

	@Override
	public boolean containsKey(Object key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException();
		int index = search(key);
		return index != -1 && buckets[index ] != null;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException();
		V res = null;
		if (containsKey(key)) {
			int index = search(key);
			res = buckets[index].getValue();
		}
		return res;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException();
		V res = null;
		int index = search(key);
		if (index != -1) {
			res = get(key);
			buckets[index] = new EntryImpl<>(key, value);
		} else {
			rehash();
			buckets[search(key)] = new EntryImpl<>(key, value);
		}
		if (res == null) size++;
		return res;
	}
	
	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key == null) throw new InvalidKeyException();
		V res = null;
		if(containsKey(key)) {
			res = get(key);
			int index = search(key);
			buckets[index] = null;
			size--;
			colapse(index);
		}
		return res;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> entries = new NodePositionList<>();
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null)	entries.addLast(buckets[i]);
		}
		return entries;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> keys = new NodePositionList<>();
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null)	keys.addLast(buckets[i].getKey());
		}
		return keys;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return Arrays.asList(buckets).iterator();
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

}